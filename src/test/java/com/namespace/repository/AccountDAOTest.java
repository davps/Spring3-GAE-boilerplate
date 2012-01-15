package com.namespace.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAOImpl;

public class AccountDAOTest extends TestBase{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccountDAOTest.class);

	private AccountDAOImpl dao;
	
	@Override
	@Before
	public void setUp(){
		super.setUp();
		
		this.objectifyFactory.register(UserGAE.class);
		this.objectifyFactory.register(Account.class);
		
		dao = new AccountDAOImpl(super.objectifyFactory);
	}
	
	@Test
	public void findAll_BoundaryConditions(){
		assertTrue(true);
	}
	
	@Test
	public void findAll_RightResults() throws InterruptedException{
		List<Account> acountListToPersist = generateAccountsAndPersistThem();

		List<Account> accountFromDatastoreList = this.dao.findAll();
		
		assertEquals(accountFromDatastoreList.size(), acountListToPersist.size());

		compareIfList1ContainsList2Objects(accountFromDatastoreList, acountListToPersist);
	}

	@Test
	public void findByUsername_BoundaryConditions(){
		assertNull(this.dao.findByUsername(null));
		
		generateAccountsAndPersistThem();
		assertNull(this.dao.findByUsername(""));
		assertNull(this.dao.findByUsername("xyz"));
		assertNull(this.dao.findByUsername("$$�!%&%/%&)=&$?*^�"));
	}
	
	@Test
	public void findByUsername_RightResults(){
		
		Objectify ofy = this.objectifyFactory.begin();
		UserGAE user = new UserGAE("user3", "33333", false);
		ofy.put(user);
		Key<UserGAE> userKey = new Key<UserGAE>(UserGAE.class, user.getUsername());

		Account account = new Account(null, "David", "D.", "example@example.com", userKey);
		
		ofy.put(account);
		
		/*
		 * It must work just if Account is at the same UserGAE entity group
		 */
		Account accountFromDatastore = ofy.query(Account.class).ancestor(userKey).get();
//		Account accountFromDatastore = ofy.query(Account.class).filter("user", userKey).get(); //without @Parent in Account.java

		assertEquals(accountFromDatastore, account);
	}

	@Test public void create_BoundaryConditions(){
		Account account = null;
		try {
			this.dao.create(account);
			fail("You have been persisted a null object!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void create_RightResults(){
		List<Account> accountListToPersist = generateAccountsAndPersistThem();
		List<Account> accountFromDatastoreList = this.objectifyFactory.begin()
									.query(Account.class).list();
		compareIfList1ContainsList2Objects(accountFromDatastoreList, 
				accountListToPersist);
		assertEquals(accountFromDatastoreList.size(), accountListToPersist.size());
	}
	
	@Test
	public void update_BoundaryConditions(){
		Account account = null;
		assertFalse(this.dao.update(account));
//		try {
//			;
//			fail("You have been persisted a null object!");
//		} catch (Exception e) {
//			assertTrue(true);
//		} 
	}
	
	@Test
	public void update_RightResults(){
		generateAccountsAndPersistThem();
		Objectify ofy = this.objectifyFactory.begin();
		Account account = ofy.query(Account.class).get();
		try {
			this.dao.update(account);
		} catch (Exception e) {
			fail();
		}finally{
			assertTrue(true);
		}
		Key<UserGAE> userKey = new Key<UserGAE>(UserGAE.class, "user");
		Account updatedAccount = ofy.query(Account.class).ancestor(userKey).get();
//		Account updatedAccount = ofy.get(Account.class, account.getId());
		assertEquals(updatedAccount, account);
	}
	
	private void compareIfList1ContainsList2Objects(
			List<Account> list1, List<Account> list2) {
		for (Iterator<Account> iterator = list2.iterator(); iterator
				.hasNext();) {
			Account account = (Account) iterator.next();
			assertTrue(list1.contains(account));
		}
	}
	
	private List<Account> generateAccountsAndPersistThem(){
		List<Account> acountListToPersist = generateAccountList();
		persistAccountList(acountListToPersist);
		return acountListToPersist;
		
	}
	
	private void persistAccountList(List<Account> list){
		Objectify ofy = this.objectifyFactory.begin();
		for (Iterator<Account> iterator = list.iterator(); iterator.hasNext();) {
			Account account = (Account) iterator.next();
			ofy.put(account);
		}
	}
	
	private List<Account> generateAccountList(){
		UserGAE user = new UserGAE("user", "12345", true);
		Key<UserGAE> userKey = new Key<UserGAE>(UserGAE.class, user.getUsername());
		Account account1 = new Account(null, "David", "D.", "example@example.com", userKey);
		
		Account account2 = new Account(null, "David", "D.", "example@example.com", userKey);
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account1);
		accountList.add(account2);
		return accountList;
	}
	
}
