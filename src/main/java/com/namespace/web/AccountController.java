package com.namespace.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.namespace.domain.Account;
import com.namespace.service.IAccountManager;
import com.namespace.service.dto.AccountDetailsForm;
import com.namespace.service.dto.AccountFormAssembler;
import com.namespace.service.validator.AccountDetailsValidator;
 
@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired private AccountFormAssembler accountFormAssembler;
	@Autowired private IAccountManager accountManager;
	@Autowired private AccountDetailsValidator accountDetailsValidator;

	public AccountController() {
	}

	public AccountController(IAccountManager accountManager, 
			AccountFormAssembler accountFormAssembler,
			AccountDetailsValidator accountDetailsValidator) {
		this.accountManager = accountManager;
		this.accountFormAssembler = accountFormAssembler;
		this.accountDetailsValidator = accountDetailsValidator;
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public ModelAndView accountHome() {
		Account enabledAccount = accountManager.getEnabledAccount();
		logger.info("Sending the enabled account for the view: " + enabledAccount);
		
		AccountDetailsForm model = this.accountFormAssembler.createAccountDetailsForm(enabledAccount); 
		
		return new ModelAndView("account/account", "account", model);
	}

	@RequestMapping(value="updateAccount", method=RequestMethod.POST)
	public String updateAccount(@ModelAttribute("account") AccountDetailsForm model, 
			BindingResult result){
		
		if(model == null)
			throw new NullPointerException("The AccountDetailsFormModel cannot be null at " + AccountController.class.toString() + "updateAccount()");
		
		this.accountDetailsValidator.validate(model, result);

		if(result.hasErrors()){
			return "account/account";
		}else{
			Account account = accountFormAssembler.copyAccountDetailsFormtoAccount(
									model, this.accountManager.getEnabledAccount());
			this.accountManager.updateAccount(account);
			return "redirect:account";
		}
	}
	
	
	
}

