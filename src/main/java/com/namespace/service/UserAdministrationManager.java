package com.namespace.service;

import java.util.List;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.util.Pair;

public interface UserAdministrationManager{

	public void createNewUserAccount(UserGAE user, Account account);

	public List<Pair<Account, UserGAE>> getEnabledUsers();

	public List<Pair<Account, UserGAE>> getDisabledUsers();
	
	public boolean deactivateUserByUsername(String username);

	public boolean deleteUserByUsername(String username);
	
	public UserGAE getUserByUsername(String username);
	
	public boolean updateUserDetails(UserGAE user, Account account);
	
	public boolean updateUser(UserGAE user);
	
}