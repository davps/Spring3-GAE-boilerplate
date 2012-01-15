package com.namespace.web;


import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import com.googlecode.objectify.Objectify;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.TestBase;
import com.namespace.service.validator.AccountDetailsValidator;
import com.namespace.util.SecurityUtil;
import com.namespace.web.AccountController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/META-INF/spring/applicationContext.xml",
	"file:src/main/resources/META-INF/spring/applicationContext-security.xml",
	"file:src/main/webapp/WEB-INF/spring/exampleServlet/mvc-config.xml",
	"file:src/main/webapp/WEB-INF/spring/exampleServlet/controllers.xml"
	})
@SuppressWarnings("unused")
public class AccountControllerIntegrationTest extends TestBase{

	private static final String ACCOUNT_FIRST_NAME = "John";
	private static final String ACCOUNT_LAST_NAME = "Doe";
	private static final String ACCOUNT_EMAIL = "a@a.com";
	private static final String USER_USERNAME = "user";
	private static final String USER_PASSWORD = "12345";

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;

	@Inject 	private ApplicationContext applicationContext;
	@Autowired 	private AccountController controller;
	@Autowired 	private AccountDetailsValidator accountDetailsValidator;
	
	@Before
	public void setUp(){
		super.setUp();
		super.objectifyFactory.register(Account.class);
		super.objectifyFactory.register(UserGAE.class);
		
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = applicationContext.getBean(HttpRequestHandlerAdapter.class);
		controller = applicationContext.getBean(AccountController.class);
		
		SecurityUtil.authenticateUser(new UserGAE(USER_USERNAME, USER_PASSWORD, true));
		createAccountAndPutIntoDatastore();
	}
	
//	@Test
//	public void accountSettingsForm(){
//		ModelAndView modelAndView = this.controller.accountHome();
//		assertEquals("account/account", modelAndView.getViewName());
//		assertNotNull(modelAndView.getModel());
//
//		AccountDetailsForm model = (AccountDetailsForm) modelAndView.getModel().get("account");
//		assertNotNull(model);
//		assertEquals(ACCOUNT_FIRST_NAME, model.getFirstName());
//		
//		/*
//		 * Boundary conditions:
//		 * When the validation find errors.
//		 */
//		request.setParameter("firstName", "");
//		request.setParameter("lastName", ACCOUNT_LAST_NAME);
//		request.setParameter("email", ACCOUNT_EMAIL);
//		WebDataBinder binder = BinderUtil.setUpBinder(new AccountDetailsForm(), accountDetailsValidator, request);
//		assertEquals(1, binder.getBindingResult().getErrorCount());
//
//		String viewName = this.controller.updateAccount((AccountDetailsForm) binder.getTarget(), binder.getBindingResult());
//		assertEquals("account/account", viewName);
//
//		/*
//		 * Right results
//		 */
//		request.setParameter("firstName", ACCOUNT_FIRST_NAME);
//		binder = BinderUtil.setUpBinder(new AccountDetailsForm(), accountDetailsValidator, request);
//		assertEquals(0, binder.getBindingResult().getErrorCount());
//		
//		viewName = this.controller.updateAccount((AccountDetailsForm) binder.getTarget(), binder.getBindingResult());
//		assertEquals("redirect:account", viewName);
//		
//		AccountDetailsForm target = (AccountDetailsForm) binder.getTarget();
//		assertEquals(ACCOUNT_FIRST_NAME, target.getFirstName());
//		assertEquals(ACCOUNT_EMAIL, target.getEmail());
//	}
	
	private void createAccountAndPutIntoDatastore(){
		Objectify ofy = this.objectifyFactory.begin();
		ofy.put(new UserGAE(USER_USERNAME, USER_PASSWORD, true));
	}

}
