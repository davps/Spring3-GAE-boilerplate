package com.namespace.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAO;
import com.namespace.repository.UserGaeDAO;

@Service
public class AccountManager extends EnabledUserManager implements IAccountManager  {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountManager.class);
	
	@Autowired private UserGaeDAO userGaeDAO;
	@Autowired private AccountDAO accountDAO;
	
	public AccountManager(UserGaeDAO userGaeDAO, 
			AccountDAO accountDAO) {
		this.userGaeDAO = userGaeDAO;
		this.accountDAO = accountDAO;
	}
	
	public AccountManager() {
	}

	@Override
	public boolean updateUser(UserGAE user) {
		logger.info("updateUser()");
		try {
			logger.info("Trying to update the user using userGaeDAO.update()");
			return this.userGaeDAO.update(user);
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean updateAccount(Account account) {
		logger.info("updateAccount()");
		
		if(account == null)
			return false;

		try {
			logger.info("Trying to update the account using  accountDAO.update() ");
			boolean isUpdatedSucessfully = this.accountDAO.update(account);
			if(isUpdatedSucessfully){
				logger.info("This account was updated sucessfully" + account.toString());
			}else{
				logger.info("This account was not updated sucessfully" + account.toString());
			}
			return isUpdatedSucessfully;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public Account getEnabledAccount() {
		UserGAE principal = this.getEnabledUser();
		return this.accountDAO.findByUsername(principal.getUsername());
	}
	
	@Override
	public boolean closeEnabledAccount(){
		UserGAE enabledUser = getEnabledUser();
		enabledUser.setEnabled(false);
		enabledUser.setAccountNonExpired(false);
		try {
			return this.userGaeDAO.update(enabledUser);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Account getAccountByUsername(String username) {
		UserGAE user = this.userGaeDAO.findByUsername(username);
		return getAccountByUser(user);
	}

	@Override
	public Account getAccountByUser(UserGAE user) {
		return this.accountDAO.findByUsername(user.getUsername());
	}

}
