package com.namespace.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.googlecode.objectify.Objectify;
import com.namespace.domain.UserGAE;
import com.namespace.repository.UserGaeDAOImpl;

public class UserGaeDAOTest extends TestBase{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserGaeDAOTest.class);
	
	private UserGaeDAOImpl dao;	

	@Before 
	@Override
	public void setUp(){
		super.setUp();
		super.objectifyFactory.register(UserGAE.class);
		dao = new UserGaeDAOImpl(super.objectifyFactory);
	}
	
	@Test 
	public void findAll_BoundaryConditions(){
		assertEquals(0, this.dao.findAll().size());
	}
	
	@Test 
	public void findAll_RightResults() throws InterruptedException{
		List<UserGAE> userListToPersist = generateUsersAndPersistThem();

		List<UserGAE> userFromDatastoreList = this.dao.findAll();
		
		assertEquals(userFromDatastoreList.size(), userListToPersist.size());

		compareIfList1ContainsList2Objects(userListToPersist, userFromDatastoreList);
	}

	@Test 
	/*
	 * TODO: write the forced errors that dispatch exceptions
	 * Some exceptions are:
	 * 
	 * http://code.google.com/intl/es-ES/appengine/docs/java/howto/maintenance.html
	 * com.google.appengine.api.memcache.MemcacheServiceException 
	 * 
	 * http://united-coders.com/christian-harms/top-7-exceptions-in-my-google-app-engine-described
	 * 
	 * http://code.google.com/intl/es-ES/appengine/articles/scaling/contention.html
	 * 
	 * http://code.google.com/intl/es-ES/appengine/articles/handling_datastore_errors.html
	 * google.appengine.ext.db.Timeout 
	 * com.google.appengine.api.datastore.DatastoreTimeoutException 
	 * 	http://code.google.com/intl/es-ES/appengine/docs/java/javadoc/com/google/appengine/api/datastore/DatastoreTimeoutException.html
	 * 	http://stackoverflow.com/questions/2123376/google-app-engine-how-do-you-handle-a-datastoretimeoutexception
	 * 
	 * http://code.google.com/intl/es-ES/appengine/docs/python/datastore/exceptions.html
	 * 
	 */
	public void findAll_ForceErrorConditions() throws InterruptedException{
		
		try {
			//coming soon
			
		} catch (DatastoreTimeoutException e) {
			// TODO: handle exception
		} catch (Exception e){
			
		}
		
	}

	@Test
	public void findAllEnabledUsers_BoundaryConditions()throws InterruptedException{
		List<UserGAE> userEnabledFromDatastoreList = this.dao.findAllEnabledUsers(true);
		assertEquals(0, userEnabledFromDatastoreList.size());

		List<UserGAE> userNotEnabledFromDatastoreList = this.dao.findAllEnabledUsers(false);
		assertEquals(0, userNotEnabledFromDatastoreList.size());
		
		List<UserGAE> userListToPersist = generateUsersAndPersistThem();
		
		assertEquals(0, this.dao.findAllEnabledUsers(true).size());
		assertEquals(userListToPersist.size(), this.dao.findAllEnabledUsers(false).size());
		
		UserGAE userEnabled = new UserGAE("userEnabled", "12345", true, true);
		Objectify ofy = this.objectifyFactory.begin();
		ofy.put(userEnabled);
		assertEquals(1, this.dao.findAllEnabledUsers(true).size());
		assertEquals(userEnabled, this.dao.findAllEnabledUsers(true).get(0));
	}
		
	@Test
	public void findAllEnabledUsers_RightResults() throws InterruptedException{
		List<UserGAE> userListToPersist = generateUsersAndPersistThem();
		List<UserGAE> userFromDatastoreList = this.dao.findAllEnabledUsers(false);
		
		compareIfList1ContainsList2Objects(userFromDatastoreList, userListToPersist);
		assertTrue(true);
		
		UserGAE user = new UserGAE("user", "12345", true, true);
		this.objectifyFactory.begin().put(user);
		List<UserGAE> userFromDatastoreList2 = this.dao.findAllEnabledUsers(true);
		assertTrue(userFromDatastoreList2.contains(user));
	}
	
	@Test
	public void create_BoundaryConditions() throws InterruptedException{
		UserGAE user = null;
		
		try {
			this.dao.create(user);
			fail("You have been persisted a null object!");
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void create_RightResults(){
		List<UserGAE> userListToPersist = generateUsersAndPersistThem();
		
		List<UserGAE> userFromDatastoreList = this.objectifyFactory.begin()
												.query(UserGAE.class).list();
		
		compareIfList1ContainsList2Objects(userListToPersist, userFromDatastoreList);
		assertEquals(userListToPersist.size(), userFromDatastoreList.size());
	}
	
	@Test
	public void create_ForceErrorConditions(){
		//TODO: create_ForceErrorConditions() Complete it.
	}
	
	@Test
	public void update_BoundaryConditions(){
		UserGAE user = null;
		assertFalse(this.dao.update(user));
		
//		try {
//			this.dao.update(user);
//			fail("You have been persisted a null object!");
//		} catch (Exception e) {
//			assertTrue(true);
//		} 
		
	}
	
	@Test
	public void update_RightResults(){
		generateUsersAndPersistThem();
		Objectify ofy = this.objectifyFactory.begin();
		UserGAE user = ofy.query(UserGAE.class).get();
		user.setPassword("0000");
		try {
			this.dao.update(user);
		} catch (Exception e) {
			fail();
		} finally {
			assertTrue(true);
		}
		UserGAE updatedUser = ofy.get(UserGAE.class, user.getUsername());
		assertEquals(updatedUser, user);
	}

	
	
	@Test
	public void update_ForceErrorConditions(){
		//TODO: update_ForceErrorConditions() Complete it.
	}
	
	@Test
	public void findByUsername_BoundaryConditions(){
		assertNull(this.dao.findByUsername(null));
		
		generateUsersAndPersistThem();
		assertNull(this.dao.findByUsername(""));
		assertNull(this.dao.findByUsername("xyz"));
		assertNull(this.dao.findByUsername("$$�!%&%/%&)=&$?*^�"));
	}
	
	@Test
	public void findByUsername_RightResults(){
		List<UserGAE> userList = generateUsersAndPersistThem();

		assertEquals(userList.get(0), 
				this.dao.findByUsername(userList.get(0).getUsername()));
	}
	
	@Test 
	public void findByUsername_ForceErrorConditions(){
		//TODO: findByUsername_ForceErrorConditions() Complete it.

	}
	private void compareIfList1ContainsList2Objects(
			List<UserGAE> list1, List<UserGAE> list2) {
		for (Iterator<UserGAE> iterator = list2.iterator(); iterator
				.hasNext();) {
			UserGAE user = (UserGAE) iterator.next();
			assertTrue(list1.contains(user));
		}
	}
	
	private List<UserGAE> generateUsersAndPersistThem() {
		List<UserGAE> userListToPersist = generateUserList();
		persistUserList(userListToPersist);
		return userListToPersist;
	}

	private List<UserGAE> generateUserList() {
		UserGAE user1 = new UserGAE("user", "12345", false);
		UserGAE user2 = new UserGAE("admin", "12345", true);
		List<UserGAE> userListToPersist = new ArrayList<UserGAE>();
		userListToPersist.add(user1);
		userListToPersist.add(user2);
		return userListToPersist;
	}

	private void persistUserList(List<UserGAE> userListToPersist) {
		Objectify ofy = this.objectifyFactory.begin();
		for (Iterator<UserGAE> iterator = userListToPersist.iterator(); iterator
				.hasNext();) {
			UserGAE user = (UserGAE) iterator.next();
			ofy.put(user);
		}
	}

	
}
