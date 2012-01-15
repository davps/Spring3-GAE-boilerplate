package com.namespace.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.service.IAccountManager;
import com.namespace.service.IUserAdministrationManager;
import com.namespace.service.dto.EnabledUserForm;
import com.namespace.service.dto.UserAdministrationForm;
import com.namespace.service.dto.UserAdministrationFormAssembler;
import com.namespace.service.validator.UserAdministrationDetailsValidator;
import com.namespace.service.validator.UserAdministrationPasswordValidator;
import com.namespace.service.validator.UserAdministrationValidator;
import com.namespace.util.Pair;

@Controller
@Secured({"ROLE_ADMIN"})
public class UsersController {

	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired private UserAdministrationFormAssembler userAdministrationFormAssembler;
	@Autowired private UserAdministrationValidator userAdministrationValidator;
	@Autowired private UserAdministrationDetailsValidator userAdministrationDetailsValidator;
	@Autowired private UserAdministrationPasswordValidator userAdministrationPasswordValidator;
	@Autowired private IUserAdministrationManager userAdministrationManager;
	@Autowired private IAccountManager accountManager;
	
	public UsersController(UserAdministrationFormAssembler userAdministrationFormAssembler,
			UserAdministrationValidator userAdministrationValidator,
			IUserAdministrationManager userAdministrationManager) {
		this.userAdministrationFormAssembler = userAdministrationFormAssembler;
		this.userAdministrationValidator = userAdministrationValidator;
		this.userAdministrationManager = userAdministrationManager;
	}
	
	public UsersController() {
	}
	
	/**
	 * New user form 
	 */
	@RequestMapping(value="/newUser", method=RequestMethod.GET)
	public ModelAndView addNewUserHome() {
		
		UserAdministrationForm model = new UserAdministrationForm();
		return new ModelAndView("users/addNewUser", "user", model);
		//return new ModelAndView("users/addNewUser", "command", "S");
	}
	
	@RequestMapping(value="createUser", method=RequestMethod.POST)
	public String createNewUser(@ModelAttribute("user") UserAdministrationForm model, 
			BindingResult result){
		
		this.userAdministrationValidator.validate(model, result);

		if(result.hasErrors()){
			return "users/addNewUser";
		}else{
			HashMap<String, Object> domainModelMap = new HashMap<String, Object>();
			domainModelMap = (HashMap<String, Object>) this.userAdministrationFormAssembler.copyNewUserFromUserAdministrationForm(model);
			UserGAE user = (UserGAE) domainModelMap.get("user");
			user.setEnabled(true);
			user.setBannedUser(false);
			user.setAccountNonExpired(true);
			Account account = (Account) domainModelMap.get("account");
			
			this.userAdministrationManager.createNewUserAccount(user, account);
			
//			Account enabledAccount = this.accountManager.getEnabledAccount();
//			Product product = this.productFormAssembler.copyNewProductFormFormtoProduct(model, new Product(), enabledAccount);
//			this.productManager.updateProduct(product);
				
			return "redirect:udpateUser/" + user.getUsername() + "/";
		}
	}
	
	
	/**
	 * Update user form 
	 */
	@RequestMapping(value="/udpateUser/{username}/", method=RequestMethod.GET)
	public ModelAndView updateUserHome(@PathVariable String username) {
		
		UserGAE user = this.userAdministrationManager.getUserByUsername(username);
		Account account = this.accountManager.getAccountByUsername(username);
		
		UserAdministrationForm userAdministrationModel = this.userAdministrationFormAssembler
				.createUserAdministrationForm(user, account);
		
		ModelAndView mv = new ModelAndView("users/updateUser");
		mv.addObject("userDetailsModel", userAdministrationModel);
		
		UserAdministrationForm userEmptyModel = new UserAdministrationForm();
		userEmptyModel.setUsername(username);
		
		mv.addObject("userPasswordModel", userAdministrationModel);
		mv.addObject("enableUserModel", userEmptyModel);
		mv.addObject("deleteBanUserModel", userEmptyModel);
	
		return mv;
	}
	
	@RequestMapping(value="/udpateUser/{username}/updateUserDetails", method=RequestMethod.POST)
	public String updateUserDetails(@PathVariable String username, @ModelAttribute("userDetailsModel") UserAdministrationForm model, 
			BindingResult result){
		
		logger.info("updateUserDetails()");
		
		this.userAdministrationDetailsValidator.validate(model, result);

		if(result.hasErrors()){
			return "udpateUser/" +  username + "/";
		}else{
			HashMap<String, Object> domainModelMap = new HashMap<String, Object>();
			domainModelMap = (HashMap<String, Object>) this.userAdministrationFormAssembler.updateUserDetailsFromUserAdministrationForm(model, this.userAdministrationManager.getUserByUsername(username), this.accountManager.getAccountByUsername(username));

			UserGAE user = (UserGAE) domainModelMap.get("user");
			Account account= (Account) domainModelMap.get("account");
			
			this.userAdministrationManager.updateUserDetails(user, account);

			return "redirect:./";
		}
	}
	
	@RequestMapping(value="/udpateUser/{username}/updatePasswordAdministrationForm", method=RequestMethod.POST)
	public String updatePassowrd(@PathVariable String username, @ModelAttribute("userPasswordModel") UserAdministrationForm model, 
			BindingResult result){
		
		logger.info("updatePassowrd()");
		
		this.userAdministrationPasswordValidator.validate(model, result);

		if(result.hasErrors()){
			logger.info("validation error!");
			return "../../udpateUser/" +  username + "/";
		}else{
			UserGAE user = this.userAdministrationManager.getUserByUsername(username);
			user.setPassword(model.getPassword());
			
			this.userAdministrationManager.updateUser(user);

			return "redirect:./";
		}
	}
	
	
	
	/**
	 * Enabled users 
	 */
	@RequestMapping(value="/enabledUsersList", method=RequestMethod.GET)
	public ModelAndView enabledUserListHome() {
		List<Pair<Account, UserGAE>> enabledUsers = this.userAdministrationManager.getEnabledUsers();

		ModelAndView mv = new ModelAndView("users/listEnabledUser");
		mv.addObject("usersList", enabledUsers);
		mv.addObject("enabledUsersToDeactivateModel", new EnabledUserForm());
		
		return mv;
	}
	
	@RequestMapping(value="deactivateUsers", method=RequestMethod.POST)
	public String deactivateUsers(@ModelAttribute("enabledUsersToDeactivateModel") EnabledUserForm model,
			BindingResult result){

		logger.info("Enabled users (account IDs) to be deactivated: " + model);
		
		List<String> deactivatedUsers = model.getDeactivate();
		
		logger.info("deactivatedUsers: " + deactivatedUsers);
		
		if(deactivatedUsers != null){
			for (Iterator<String> iterator = deactivatedUsers.iterator(); iterator.hasNext();) {
				String username = (String) iterator.next();
				this.userAdministrationManager.deactivateUserByUsername(username);
			}
		}
		
		return "redirect:enabledUsersList";
	}
	
	
	
	/**
	 * Disabled users
	 */
	@RequestMapping(value="/disabledUsersList", method=RequestMethod.GET)
	public ModelAndView disabledUserListHome() {
		
		List<Pair<Account, UserGAE>> disabledUsers = this.userAdministrationManager.getDisabledUsers();

		ModelAndView mv = new ModelAndView("users/listDisabledUser");
		mv.addObject("usersList", disabledUsers);
		mv.addObject("enabledUsersToDeleteModel", new EnabledUserForm());
		
		return mv;
	}
	
	@RequestMapping(value="deleteUsers", method=RequestMethod.POST)
	public String deleteUsers(@ModelAttribute("enabledUsersToDeleteModel") EnabledUserForm model,
			BindingResult result){

		logger.info("Enabled users (account IDs) to be deleted: " + model);
		
		List<String> deletedUsers = model.getDeactivate();
		
		logger.info("deactivatedUsers: " + deletedUsers);
		
		if(deletedUsers != null){
			for (Iterator<String> iterator = deletedUsers.iterator(); iterator.hasNext();) {
				String username = (String) iterator.next();
				this.userAdministrationManager.deleteUserByUsername(username);
			}
		}
		
		return "redirect:disabledUsersList";
	}
	
	
}
