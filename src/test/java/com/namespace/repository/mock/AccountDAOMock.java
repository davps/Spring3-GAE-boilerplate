package com.namespace.repository.mock;

import com.googlecode.objectify.ObjectifyFactory;
import com.namespace.domain.Account;
import com.namespace.repository.AccountDAOImpl;

public class AccountDAOMock extends AccountDAOImpl{

	public AccountDAOMock(ObjectifyFactory objectifyFactory) {
		super(objectifyFactory);
		objectifyFactory.register(Account.class);
	}
	
}
