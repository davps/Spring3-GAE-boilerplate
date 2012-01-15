package com.namespace.repository;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.cache.ListenableHook;

/**
 * All tests should extend this class to set up the GAE environment.
 * @see <a href="http://code.google.com/appengine/docs/java/howto/unittesting.html">Unit Testing in Appengine</a>
 */
public class TestBase
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(TestBase.class.getName());
	
	protected ObjectifyFactory objectifyFactory;
	
	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(
					new LocalDatastoreServiceTestConfig(),
					new LocalMemcacheServiceTestConfig(),
					new LocalTaskQueueTestConfig()
				);

	@Before
	public void setUp()
	{
		this.helper.setUp();
		
		this.objectifyFactory = new ObjectifyFactory() {
			@Override
			public Objectify begin(ObjectifyOpts opts)
			{
				// This can be used to enable/disable the memory cache globally.
				opts.setGlobalCache(true);
				
				// This can be used to enable/disable the session caching objectify
				// Note that it will break several unit tests that check for transmutation
				// when entities are run through the DB (ie, unknown List types become
				// ArrayList).  These failures are ok.
				opts.setSessionCache(false);
				
				return super.begin(opts);
			}
		};
		
		/*
		 * Register your classes here or override this method,
		 * I always override this method and then I call this method using super.setUp()	
		 */
		//this.fact.register(Libro.class);
		
	}

	@After
	public void tearDown()
	{
		// This normally is done in the AsyncCacheFilter but that doesn't exist for tests
		ListenableHook.completeAllPendingFutures();
		this.helper.tearDown();
	}
	
	/** 
	 * Utility methods that puts and immediately gets an entity 
	 */
	protected <T> T putAndGet(T saveMe)
	{
		Objectify ofy = this.objectifyFactory.begin();
		
		Key<T> key = ofy.put(saveMe);

		try
		{
			Entity ent = ofy.getDatastore().get(objectifyFactory.getRawKey(key));
			System.out.println(ent);
		}
		catch (EntityNotFoundException e) { throw new RuntimeException(e); }

		return ofy.find(key);
	}
}