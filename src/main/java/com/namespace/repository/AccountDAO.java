package com.namespace.repository;

import java.util.List;

import com.namespace.domain.Account;

public interface AccountDAO extends GenericDAO<Account>{

	public List<Account> findAll();

	public Account findByUsername(String username);
	
}