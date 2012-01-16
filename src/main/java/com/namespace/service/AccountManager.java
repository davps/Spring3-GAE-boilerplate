package com.namespace.service;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;

public interface AccountManager extends CurrentUserManager{

	
	public boolean updateAccount(Account account);
	public Account getEnabledAccount();
	public Account getAccountByUsername(String username);
	public Account getAccountByUser(UserGAE user);
	public boolean updateUser(UserGAE user);
	public boolean closeEnabledAccount();

	
}
