package com.namespace.service;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.namespace.domain.UserGAE;
import com.namespace.repository.UserGaeDAO;
import com.namespace.repository.TestBase;
import com.namespace.repository.mock.UserGaeDAOMock;
import com.namespace.service.CustomUserDetailsService;

public class CustomUserDetailsServiceTest extends TestBase{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceTest.class);

	private UserDetailsService manager;
	
	private UserGaeDAO userGaeDAO;

	@Before
	public void setUp(){
		super.setUp();
		this.userGaeDAO = new UserGaeDAOMock(super.objectifyFactory);
		
		this.manager = new CustomUserDetailsService(userGaeDAO);
	}
	
	@Test
	public void loadUserByUsername(){
		//TODO: uncomment
		/*
		 * BoundaryConditions
		 */
		@SuppressWarnings("unused")
		UserDetails defaultUserWhenDatastoreIsEmpty = this.manager.loadUserByUsername(null);
		UserGAE userTest = new UserGAE("user", "user", true, true, false); 
//		assertEquals(userTest, defaultUserWhenDatastoreIsEmpty);
		
		super.putAndGet(userTest);
		assertNull(this.manager.loadUserByUsername(null));
		
		/*
		 * Right results
		 */
//		assertEquals(userTest, this.manager.loadUserByUsername(userTest.getUsername()));
		
		
	}
	
}
