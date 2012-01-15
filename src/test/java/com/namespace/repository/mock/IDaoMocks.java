package com.namespace.repository.mock;

import com.googlecode.objectify.Objectify;

public interface IDaoMocks {

	public void datastoreWithNoEntities(Objectify ofy);

	public void datastoreWithOneEntity(Objectify ofy);

	public void datastoreWithManyEntities(Objectify ofy);

}