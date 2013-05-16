package com.namespace.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Key;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.repository.AccountDAO;
import com.namespace.repository.UserGaeDAO;
import com.namespace.web.HomeController;

@Service(value="customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired private UserGaeDAO userGaeDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	
	
	
	public CustomUserDetailsService() {
	}
	
	public CustomUserDetailsService(UserGaeDAO userGaeDAO) {
		this.userGaeDAO = userGaeDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		UserGAE user = this.userGaeDAO.findByUsername(username);
		
		logger.info("checking user services.... " + user);		
		
		return user;
			
	}

}
