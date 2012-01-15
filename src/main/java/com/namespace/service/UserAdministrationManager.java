package com.namespace.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Key;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAO;
import com.namespace.repository.UserGaeDAO;
import com.namespace.util.Pair;

@Service
@Secured({"ROLE_ADMIN"})
public class UserAdministrationManager extends EnabledUserManager implements IUserAdministrationManager {

	private static final Logger logger = LoggerFactory.getLogger(UserAdministrationManager.class);

	@Autowired private UserGaeDAO userGaeDAO;
	@Autowired private AccountDAO accountDAO; 
	
	public UserAdministrationManager(UserGaeDAO userGaeDAO,
			AccountDAO accountDAO) {
		this.userGaeDAO = userGaeDAO;
		this.accountDAO = accountDAO;
	}
	
	public UserAdministrationManager() {
	}

	@Override
	public void createNewUserAccount(UserGAE user, Account account){
		logger.info("createNewUserAccount()");
		
		try {
			logger.info("Trying to create a new user: " + user.toString());
			this.userGaeDAO.create(user);
			logger.info("New user created successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Key<UserGAE> userKey = new Key<UserGAE>(UserGAE.class, user.getUsername());
		account.setUser(userKey);
		try {
			logger.info("Trying to create a new account: " + account.toString());
			this.accountDAO.create(account);
			logger.info("New account created successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Pair<Account, UserGAE>> getEnabledUsers(){
		List<UserGAE> enabledUsers = this.userGaeDAO.findAllEnabledUsers(true);
		
		
		List<Pair<Account, UserGAE>> enabledAccounts = new ArrayList<Pair<Account,UserGAE>>();
		for (Iterator<UserGAE> iterator = enabledUsers.iterator(); iterator.hasNext();) {
			UserGAE enabledUser = (UserGAE) iterator.next();
			enabledAccounts.add(new Pair<Account, UserGAE>(this.accountDAO.findByUsername(enabledUser.getUsername()), enabledUser));
		}
		
//		List<Account> enabledAccounts = new ArrayList<Account>();
//		for (Iterator<UserGAE> iterator = enabledUsers.iterator(); iterator.hasNext();) {
//			UserGAE enabledUser = (UserGAE) iterator.next();
//			enabledAccounts.add(this.accountDAO.findByUsername(enabledUser.getUsername()));
//		}
		return enabledAccounts;
	}

	@Override
	public List<Pair<Account, UserGAE>> getDisabledUsers(){
		List<UserGAE> noEnabledUsers = this.userGaeDAO.findAllEnabledUsers(false);
		
		List<Pair<Account, UserGAE>> noEnabledAccounts = new ArrayList<Pair<Account,UserGAE>>();
		for (Iterator<UserGAE> iterator = noEnabledUsers.iterator(); iterator.hasNext();) {
			UserGAE enabledUser = (UserGAE) iterator.next();
			noEnabledAccounts.add(new Pair<Account, UserGAE>(this.accountDAO.findByUsername(enabledUser.getUsername()), enabledUser));
		}
		return noEnabledAccounts;
	}

	@Override
	public boolean deactivateUserByUsername(String username) {
		
		UserGAE user = this.userGaeDAO.findByUsername(username);
		user.setEnabled(false);
		try {
			return this.userGaeDAO.update(user);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteUserByUsername(String username) {
		UserGAE user = this.userGaeDAO.findByUsername(username);
		Account account = this.accountDAO.findByUsername(username);
		
		try {
			return this.userGaeDAO.remove(user) && this.accountDAO.remove(account);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserGAE getUserByUsername(String username) {
		return this.userGaeDAO.findByUsername(username);
	}

	@Override
	public boolean updateUserDetails(UserGAE user, Account account) {
		try {
			return updateUser(user) && this.accountDAO.update(account);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(UserGAE user) {

		try {
			return this.userGaeDAO.update(user);
		} catch (Exception e) {
			return false;
		}
	}
}

