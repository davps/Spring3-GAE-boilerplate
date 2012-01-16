package com.namespace.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAO;
import com.namespace.repository.UserGaeDAO;
import com.namespace.repository.TestBase;
import com.namespace.repository.mock.AccountDAOMock;
import com.namespace.repository.mock.UserGaeDAOMock;
import com.namespace.service.AccountManagerImpl;
import com.namespace.service.AccountManager;

public class AccountManagerTest extends TestBase{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccountManagerTest.class);

	private AccountManager manager;
	
	private UserGaeDAO userGaeDAO;
	private AccountDAO accountDAO;
	
	@Before
	public void setUp(){
		super.setUp();
		
		this.userGaeDAO = new UserGaeDAOMock(super.objectifyFactory);
		this.accountDAO = new AccountDAOMock(super.objectifyFactory);

		this.manager = new AccountManagerImpl(userGaeDAO, accountDAO);
	}
	
	@Test
	public void updateUser_BoundaryConditions(){
		
		assertFalse(this.manager.updateUser(null));
		assertFalse(this.manager.updateUser(new UserGAE()));
		Objectify ofy = super.objectifyFactory.begin();
		assertEquals(0, ofy.query(UserGAE.class).list().size());
		assertFalse(this.manager.updateUser(new UserGAE("user1", "12345", true)));
	}
	
	@Test
	public void updateUser_RightConditions(){
		Objectify ofy = super.objectifyFactory.begin();
		
		UserGAE user = new UserGAE("user", "12345", true);
		ofy.put(user);
		
		user.setPassword("AAAAAAAAAA");
		assertTrue(this.manager.updateUser(user));
		assertEquals(user, ofy.query(UserGAE.class).get());
		assertEquals(1, ofy.query(UserGAE.class).filter("username", user.getUsername()).list().size());
	}
	
	@Test
	public void updateAccount(){
		Objectify ofy = super.objectifyFactory.begin();
		
		/*
		 * BoundaryConditions
		 */
		assertFalse(this.manager.updateAccount(null));
		assertFalse(this.manager.updateAccount(new Account()));
		
		UserGAE user = new UserGAE("user", "12345", true);
		Account account = new Account(null, "David", "D.", "example@example.com", null);
		assertFalse(this.manager.updateAccount(account));
		
		ofy.put(account);
		assertFalse(this.manager.updateAccount(account));

		/*
		 * Right results
		 */
		ofy.put(user);
		account.setUser(new Key<UserGAE>(UserGAE.class, user.getUsername()));
		ofy.put(account);
		assertTrue(this.manager.updateAccount(account));
		
		Account accountFromDatastore = ofy.query(Account.class).ancestor(user).get();
		assertEquals(account, accountFromDatastore); 
		
	}
	
	@Test
	public void getEnabledAccount(){
		//TODO: implement this test: getEnabledAccount()
	}
	
	@Test
	public void getQuotaForEnabledAccount(){
		//TODO: implement this test: getEnabledAccount()
	}
}
