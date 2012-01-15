package com.namespace.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.TestBase;
import com.namespace.repository.UserGaeDAOImpl;
import com.namespace.service.validator.UserAdministrationValidator;
import com.namespace.util.SecurityUtil;
import com.namespace.web.UsersController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/META-INF/spring/applicationContext.xml",
	"file:src/main/resources/META-INF/spring/applicationContext-security.xml",
	"file:src/main/webapp/WEB-INF/spring/exampleServlet/mvc-config.xml",
	"file:src/main/webapp/WEB-INF/spring/exampleServlet/controllers.xml"
	})
@SuppressWarnings("unused")
public class UsersControllerIntegrationTest extends TestBase {

	private static final Logger logger = LoggerFactory.getLogger(UsersControllerIntegrationTest.class);

	private static final String USER_USERNAME = "user";
	private static final String USER_PASSWORD = "12345";
	private static final String ACCOUNT_FIRST_NAME = "John";
	private static final String ACCOUNT_LAST_NAME = "Doe";
	private static final String ACCOUNT_EMAIL = "a@a.com";
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;

	@Inject 	private ApplicationContext applicationContext;
	@Autowired 	private UsersController controller;
	@Autowired	private UserAdministrationValidator userAdministrationValidator;
	@Autowired 	private UserGaeDAOImpl userGaeDAOImpl;
	
	@Before
	public void setUp(){
		super.setUp();
		super.objectifyFactory.register(UserGAE.class);
		super.objectifyFactory.register(Account.class);

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = applicationContext.getBean(HttpRequestHandlerAdapter.class);
		controller = applicationContext.getBean(UsersController.class);
		
		SecurityUtil.authenticateUser(new UserGAE(USER_USERNAME, USER_PASSWORD, true, true));
		createAccountAndPutIntoDatastore();
	}
	
//	@Test
//	public void newUseForm(){
//		ModelAndView modelAndView = this.controller.addNewUserHome();
//		assertEquals("users/addNewUser", modelAndView.getViewName());
//		assertNotNull(modelAndView.getModel());
//		
//		UserAdministrationForm model = (UserAdministrationForm) modelAndView.getModel().get("user");
//		assertNotNull(model);
//		System.out.println(model);
//		assertNull(model.getUsername());
//		
//		WebDataBinder binder = BinderUtil.setUpBinder(new UserAdministrationForm(), 
//													  userAdministrationValidator, 
//													  request);
//		assertEquals(4, binder.getBindingResult().getErrorCount());
//		String viewName = this.controller.createNewUser((UserAdministrationForm) binder.getTarget(), binder.getBindingResult());
//		assertEquals("users/addNewUser", viewName);
//
//		
//		/*
//		 * Right results
//		 */
//		request.setParameter("username", USER_USERNAME);
//		request.setParameter("firstName", ACCOUNT_FIRST_NAME);
//		request.setParameter("lastName", ACCOUNT_LAST_NAME);
//		request.setParameter("email", ACCOUNT_EMAIL);
//		request.setParameter("schedulingEmail", ACCOUNT_EMAIL);
//		request.setParameter("accountType", AccountType.Premium.toString());
//
//		binder = BinderUtil.setUpBinder(new UserAdministrationForm(), 
//									    userAdministrationValidator, 
//									    request);
//		assertEquals(0, binder.getBindingResult().getErrorCount());
//		
//		viewName = this.controller.createNewUser((UserAdministrationForm) binder.getTarget(), binder.getBindingResult());
//		assertEquals("redirect:newUser", viewName);
//
//		UserAdministrationForm target = (UserAdministrationForm) binder.getTarget();
//		
//		assertEquals(USER_USERNAME, target.getUsername());
//		assertEquals(ACCOUNT_FIRST_NAME, target.getFirstName());
//		assertEquals(ACCOUNT_LAST_NAME, target.getLastName());
//		assertEquals(ACCOUNT_EMAIL, target.getEmail());
//		assertEquals(ACCOUNT_EMAIL, target.getSchedulingEmail());
//		assertEquals(AccountType.Premium, target.getAccountType());
//
//	}
	
	@Test
	public void enabledUsers(){
		
		//TODO: I need to uncomment and fix that
//		ModelAndView modelAndView = this.controller.enabledUserListHome(new ModelMap());
//		assertEquals("users/listEnabledUser", modelAndView.getViewName());
//		assertNotNull(modelAndView.getModel());
//		
//		checkUsers(modelAndView);
		
	}
	
	@Test
	public void disabledUsers(){
//		throw new UnsupportedOperationException();
//		ModelAndView modelAndView = this.controller.disabledUserListHome(new ModelMap());
//		assertEquals("users/listDisabledUser", modelAndView.getViewName());
//		assertNotNull(modelAndView.getModel());
//
//		Objectify ofy = this.objectifyFactory.begin();
//		UserGAE user = ofy.query(UserGAE.class).get();
//		user.setEnabled(false);
//		ofy.put(user);

		/*
		 * TODO I need to test the boundary conditions too.
		 */
		/*
		 * TODO: Fix this bug. I need to investigate what happens.
		 */
//		checkUsers(modelAndView);

	}

	private void checkUsers(ModelAndView modelAndView) {
		ModelMap model = (ModelMap) modelAndView.getModel().get("user");
		@SuppressWarnings("unchecked")
		List<Account> usersList = (List<Account>) model.get("usersList");
		assertNotNull(model);
		assertNotNull(usersList);
		assertEquals(usersList.get(0).getFirstName(), ACCOUNT_FIRST_NAME);
		assertEquals(usersList.get(0).getLastName(), ACCOUNT_LAST_NAME);
		assertEquals(usersList.get(0).getEmail(), ACCOUNT_EMAIL);
	}
	
	private void createAccountAndPutIntoDatastore(){
		Objectify ofy = this.objectifyFactory.begin();
		Key<UserGAE> userKey = ofy.put(new UserGAE(USER_USERNAME, USER_PASSWORD, true, true));
		ofy.put(new Account(null, ACCOUNT_FIRST_NAME, ACCOUNT_LAST_NAME, ACCOUNT_EMAIL, userKey));
		
	}
	
}
