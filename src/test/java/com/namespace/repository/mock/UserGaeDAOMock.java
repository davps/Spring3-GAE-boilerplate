package com.namespace.repository.mock;

import com.googlecode.objectify.ObjectifyFactory;
import com.namespace.domain.UserGAE;
import com.namespace.repository.UserGaeDAOImpl;

public class UserGaeDAOMock extends UserGaeDAOImpl{
	
	public UserGaeDAOMock(ObjectifyFactory objectifyFactory) {
		super(objectifyFactory);
		objectifyFactory.register(UserGAE.class);
	}

}
