package com.namespace.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.objectify.Key;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAO;
import com.namespace.repository.UserGaeDAO;
import com.namespace.service.AbstractCurrentUserManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends AbstractCurrentUserManager {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private UserGaeDAO userGaeDAO;
	@Autowired private AccountDAO accountDAO;	

	public HomeController() {
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		return "home";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
 
		return "login";
 
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
 
		return "home"; 
	}	
	
	@RequestMapping(value="/createDefaultUsers", method = RequestMethod.GET)
	public String createDefaultUsers(ModelMap model) {
		try{
			logger.info("Creating default accounts...");
			
			UserGAE firstAdminUser = new UserGAE("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", true, true, false, true);
			UserGAE firstNonAdminUser = new UserGAE("user", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb", false, true, false, true);
			Key<UserGAE> userAdminKey = new Key<UserGAE>(UserGAE.class, firstAdminUser.getUsername());
			Key<UserGAE> userNonAdminKey = new Key<UserGAE>(UserGAE.class, firstNonAdminUser.getUsername());
			Account accountAdmin = new Account(null, "John", "Doe", "example@example.com", userAdminKey);
			Account accountNonAdmin = new Account(null, "User1", "User1", "example1@example.com", userNonAdminKey);

			this.userGaeDAO.create(firstAdminUser);
			this.userGaeDAO.create(firstNonAdminUser);
			this.accountDAO.create(accountAdmin);
			this.accountDAO.create(accountNonAdmin);
			
			model.addAttribute("Error", "true");
		}catch (Exception ex){
			model.addAttribute("Error", "true");
		}
		return "home";
 
	}	
	

}

