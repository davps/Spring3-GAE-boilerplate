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
public class UserGaeDAOImpl implements UserGaeDAO {
	
    @Autowired private ObjectifyFactory objectifyFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserGaeDAOImpl.class);

	public UserGaeDAOImpl() {
	}
	
	public UserGaeDAOImpl(ObjectifyFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}
	
	@Override
	public List<UserGAE> findAll() {
		Objectify ofy = objectifyFactory.begin();
		
		Query<UserGAE> q = ofy.query(UserGAE.class);
		ArrayList<UserGAE> users = (ArrayList<UserGAE>) q.list();
		
		return users;
	}

	@Override
	public List<UserGAE> findAllEnabledUsers(boolean isEnabled){
		try {
			Objectify ofy = objectifyFactory.begin();
			
			Query<UserGAE> q = ofy.query(UserGAE.class).filter("enabled", isEnabled);

			UserGAE user = ofy.query(UserGAE.class).get();
			logger.info("**********************user:" + user.toString());

			ArrayList<UserGAE> users = (ArrayList<UserGAE>) q.list();
			

			logger.info("retrieving the users list from the datastore: " 
					+ users.toString());
			
			return users;
			
		} catch (Exception e) {
			logger.info("cannot retrieve the users " +  
					 		"from the datastore. Should be for two reasons: " +
							"The account associated with this user doest'n exist, of " +
							"they are not any accounts in the datastore");
			return new ArrayList<UserGAE>();
		}
		
	}
	
	@Override
	public void create(UserGAE user) throws Exception {
		if(user != null){
			Objectify ofy = objectifyFactory.begin();
			ofy.put(user);
		}else{
			throw new Exception("You can't create a null user");
		}
	}
	
	@Override
	public boolean update(UserGAE user) {

		if(user == null)
			return false;

		Objectify ofy = objectifyFactory.begin();
		
		boolean thisAccountAlreadyExist =  ofy.query(UserGAE.class).get() != null;
		
		if(thisAccountAlreadyExist){
			ofy.put(user);
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean remove(UserGAE user) {
		Objectify ofy = objectifyFactory.begin();
		ofy.delete(user);
		return true;
	}

	@Override
	public UserGAE findByUsername(String username){
		try {
			Objectify ofy = objectifyFactory.begin();
			UserGAE user = ofy.get(UserGAE.class, username);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	//TODO:Eliminar esta clase
	@Override
	public void createUserAccount(UserGAE user, Account account){
		
		Objectify ofy = objectifyFactory.beginTransaction();
		try {
			logger.info("ofy.put(user) will be realized now");
			ofy.put(user);
			logger.info("ofy.put(user) was realized sucessfully");

			Key<UserGAE> userGaeKey = new Key<UserGAE>(UserGAE.class, user.getUsername());
			logger.info("The username to be stored is:" + user.getUsername());
			account.setUser(userGaeKey);
			logger.info("ofy.put(account) will be realized now");
			ofy.put(account);
			logger.info("ofy.put(account) was realized sucessfully");
			
//			Key<Account> accountKey = new Key<Account>(Account.class, account.getId());
			
//			logger.info("accountKey:" + accountKey.toString());
//			scheduler.setAccount(new Key<Account>(Account.class, account.getId()));
			logger.info("ofy.put(scheduler) will be realized now");
			//ofy.put(scheduler);
			logger.info("ofy.put(scheduler) was realized sucessfully");
			
			ofy.getTxn().commit();

		} catch (Exception e) {
			logger.info("The transaction createUserAccount() failed");
			logger.info("\nThe error: \n" + e.getMessage() );
		}finally{
			if(ofy.getTxn().isActive())
				ofy.getTxn().rollback();
		}
		
		throw new UnsupportedOperationException(); 


	}

	@Override
	public UserGAE findByAccount(Account account) {
		Objectify ofy = this.objectifyFactory.begin();
		
		UserGAE user = ofy.find(account.getUser());
		
		return user;
	}
	
}

