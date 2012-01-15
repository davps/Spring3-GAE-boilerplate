package com.namespace.repository;

import java.util.List;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;

public interface UserGaeDAO extends GenericDAO<UserGAE>{

	public List<UserGAE> findAll();

	public List<UserGAE> findAllEnabledUsers(boolean isEnabled);

	public UserGAE findByUsername(String username);
	
	public UserGAE findByAccount(Account account);

	public void createUserAccount(UserGAE user, Account account);

}