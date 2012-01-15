package com.namespace.service.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.googlecode.objectify.Key;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.TestBase;
import com.namespace.service.IAccountManager;
import com.namespace.service.IEnabledUserManager;
import com.namespace.util.SecurityUtil;

public class AccountManagerMock extends TestBase implements IAccountManager, IEnabledUserManager{

	private List<Account> accounts = new ArrayList<Account>();
	private List<UserGAE> users = new ArrayList<UserGAE>();

	public AccountManagerMock(UserGAE enabledUser) {
		SecurityUtil.authenticateUser(enabledUser);
		
		/*
		 * Without extending TestBase.java and calling super.seUp() the 
		 * com.googlecode.objectify.Key class doesn't work.
		 * It's mandatory. 
		 */
		super.setUp();
//		super.objectifyFactory.register(Quota.class);
//		super.objectifyFactory.register(Scheduler.class);
//		super.objectifyFactory.register(Account.class);
//		super.objectifyFactory.register(UserGAE.class);
//		super.objectifyFactory.register(Product.class);
//		super.objectifyFactory.register(MarketplaceProduct.class);
	}
	
	@Override
	public boolean updateAccount(Account account) {
		for (Iterator<Account> iterator = accounts.iterator(); iterator.hasNext();) {
			Account accountInMemory = (Account) iterator.next();

			if(accountInMemory.getId().equals(account.getId())){
				accounts.add(accounts.indexOf(accountInMemory), account);
				return true;
			}
		}
		return false;
	}

	@Override
	public Account getEnabledAccount() {
		return accounts.get(0);
	}

	@Override
	public boolean updateUser(UserGAE user) {
		for (Iterator<UserGAE> iterator = users.iterator(); iterator.hasNext();) {
			UserGAE userInMemory = (UserGAE) iterator.next();

			if(userInMemory.getUsername().equals(user.getUsername())){
				users.add(users.indexOf(userInMemory), user);
				return true;
			}
		}
		return false;
	}


	@Override
	public UserGAE getEnabledUser() {
		UserGAE principal = (UserGAE) SecurityContextHolder.getContext()
														   .getAuthentication()
														   .getPrincipal();
		return principal;
	}
	
	public void createInMemoryDomainObjects(){
		users = new ArrayList<UserGAE>();
		accounts = new ArrayList<Account>();

		UserGAE user = new UserGAE("user", "12345", true);
		Key<UserGAE> userKey = new Key<UserGAE>(UserGAE.class, "user");
		users.add(user);
		
		Account account = new Account(new Long(1), "David", "D.", "example@example.com", userKey);
		accounts.add(account);
		
		UserGAE user2 = new UserGAE("user2", "12345", false);
		Key<UserGAE> userKey2 = new Key<UserGAE>(UserGAE.class, "user2");
		users.add(user2);

		Account account2 = new Account(new Long(2), "David", "D.", "example@example.com", userKey2);
		accounts.add(account2);

	}

	@Override
	public boolean closeEnabledAccount() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountByUser(UserGAE user) {
		// TODO Auto-generated method stub
		return null;
	}


}
