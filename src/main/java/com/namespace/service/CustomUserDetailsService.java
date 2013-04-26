package com.namespace.service;

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

@Service(value="customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired private UserGaeDAO userGaeDAO;
	@Autowired private AccountDAO accountDAO;
	
	
	public CustomUserDetailsService() {
	}
	
	public CustomUserDetailsService(UserGaeDAO userGaeDAO) {
		this.userGaeDAO = userGaeDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		UserGAE user = this.userGaeDAO.findByUsername(username);
		if(!this.userGaeDAO.findAll().isEmpty()){
			return user;
		}else{
			/*
			 * Create a default user if the datastore is empty
			 */
			
			UserGAE firstAdminUser = new UserGAE("admin", "admin", true, true, false, true);
			UserGAE firstNonAdminUser = new UserGAE("user", "user", false, true, false, true);
			Key<UserGAE> userAdminKey = new Key<UserGAE>(UserGAE.class, firstAdminUser.getUsername());
			Key<UserGAE> userNonAdminKey = new Key<UserGAE>(UserGAE.class, firstNonAdminUser.getUsername());
			Account accountAdmin = new Account(null, "John", "Doe", "example@example.com", userAdminKey);
			Account accountNonAdmin = new Account(null, "User1", "User1", "example1@example.com", userNonAdminKey);
			try {
				this.userGaeDAO.create(firstAdminUser);
				this.userGaeDAO.create(firstNonAdminUser);
				this.accountDAO.create(accountAdmin);
				this.accountDAO.create(accountNonAdmin);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.userGaeDAO.findByUsername(firstAdminUser.getUsername());
		}
			
	}

}
