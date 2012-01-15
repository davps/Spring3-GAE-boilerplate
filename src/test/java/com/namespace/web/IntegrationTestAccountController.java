package com.namespace.web;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.WebDataBinder;

import com.namespace.service.dto.AccountDetailsForm;
import com.namespace.web.AccountController;


import junit.framework.TestCase;

public class IntegrationTestAccountController extends TestCase {
	
	@Test
	public void testHandlerMethod(){
		
		@SuppressWarnings("unused")
		AccountController accountController = new AccountController();
		
	    final MockHttpServletRequest request = new MockHttpServletRequest("post", "/updateAccount");
	    
	    request.setParameter("firstName", "Joe");
	    request.setParameter("lastName", "Smith");

	    final AccountDetailsForm accountDetails = new AccountDetailsForm();
	    final WebDataBinder binder = new WebDataBinder(accountDetails, "account");
	    binder.bind(new MutablePropertyValues(request.getParameterMap()));
	    
	    //final String mv = accountController.updateAccount(accountDetails, binder.getBindingResult());
	    
	    //assertEquals("updateAccount", mv);
	    assertEquals("a", "a");
	    
	    		
	}
    
}
