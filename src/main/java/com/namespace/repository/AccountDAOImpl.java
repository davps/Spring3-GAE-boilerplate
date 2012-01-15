package com.namespace.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;

@Component
public class AccountDAOImpl implements AccountDAO {

	private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Autowired private ObjectifyFactory objectifyFactory;
    
    public AccountDAOImpl() {
	}
    
    public AccountDAOImpl(ObjectifyFactory objectifyFactory) {
    	if(objectifyFactory != null)
    		logger.info("objectifyFactory was injected succesfully to accountDao: " + 
    				objectifyFactory.toString()); 
    		
		this.objectifyFactory = objectifyFactory;
	}

	
	@Override
	public List<Account> findAll() {
		Objectify ofy = objectifyFactory.begin();
		
		Query<Account> q = ofy.query(Account.class);
		ArrayList<Account> accounts = (ArrayList<Account>) q.list();

		logger.info("retrieving the accounts from the datastore: " + accounts.toString());
		
		return accounts;
	}

	@Override
	public Account findByUsername(String username){
		try {
			Objectify ofy = objectifyFactory.begin();
			
			Key<UserGAE> userGaeKey = new Key<UserGAE>(UserGAE.class, username);

			Query<Account> q = ofy.query(Account.class).ancestor(userGaeKey);
			Account account = q.get();

			logger.info("retrieving this account from the datastore: " + account.toString());
			
			return account;
			
		} catch (Exception e) {
			logger.info("cannot retrieve the " + username + "'s account from the datastore. Should be for two reasons: The account associated with this user doest'n exist, of they are not any accounts in the datastore");
			return null;
		}
	}


	@Override
	public void create(Account account) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(account);
	}

	@Override
	public boolean update(Account account) {
		logger.info("update()");

		if(account == null)
			return false;
		
		Objectify ofy = objectifyFactory.begin();

		logger.info("verify if this account already exist " +
					"in the datastore: " + account.toString());
		boolean thisAccountAlreadyExist =  ofy.query(Account.class)
											  .ancestor(account.getUser())
											  .get() != null;
		
		if(thisAccountAlreadyExist){
			logger.info("Confirmed: this account already exist.");
			ofy.put(account);
			return true;
		}else{
			logger.info("This account doesn't exist at the datastore or " +
					"something whas wrong (might be the ancestor reference");
			return false;
		}
	}

	@Override
	public boolean remove(Account item) {
		Objectify ofy = objectifyFactory.begin();
		ofy.delete(item);
		return true;
	}

}
