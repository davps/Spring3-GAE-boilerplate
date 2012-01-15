package com.namespace.web;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.namespace.domain.UserGAE;
import com.namespace.service.dto.AccountDetailsForm;
import com.namespace.service.dto.AccountFormAssembler;
import com.namespace.service.mock.AccountManagerMock;
import com.namespace.service.validator.AccountDetailsValidator;
import com.namespace.web.AccountController;

public class AccountControllerTest {
	
	private AccountController controller;
	private AccountManagerMock accountManager; 
	
	@Before
	public void setUp(){

		UserGAE user = new UserGAE("user", "12345", true);
		accountManager = new AccountManagerMock(user);
		
		AccountFormAssembler accountFormAssembler = new AccountFormAssembler();
		AccountDetailsValidator accountDetailsValidator = new AccountDetailsValidator();
		
		controller = new AccountController(accountManager, 
												  accountFormAssembler, 
												  accountDetailsValidator);
	}
	
	@Test
	public void accountHome(){
		accountManager.createInMemoryDomainObjects();
		ModelAndView modelAndView = this.controller.accountHome();
		assertEquals("account/account", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		
		AccountDetailsForm model = (AccountDetailsForm) modelAndView.getModel().get("account");
		assertNotNull(model);
		assertEquals("David", model.getFirstName());
	}
	
	@Test
	public void updateAccount(){
		try {
			this.controller.updateAccount(null, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e){
			fail();
		}
		
		AccountDetailsForm model = new AccountDetailsForm();
		BindingResult bindingResult = new BeanPropertyBindingResult(model, "account");
		assertEquals("account/account", this.controller.updateAccount(model, bindingResult));
	}
	
	
}
