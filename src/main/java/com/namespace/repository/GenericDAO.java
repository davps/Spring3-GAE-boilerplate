package com.namespace.repository;

public interface GenericDAO<T> {
	
	public void create(T item) throws Exception;
	
	public boolean update(T item) throws Exception;
	
	public boolean remove(T item) throws Exception;
	
}
