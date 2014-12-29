package com.project.commons.dao;

import java.io.Serializable;
import java.util.List;

/**
 * generic inteface for data access object, to be extended or implemented
 * Contains common persistence method.
 * 
 * @author Dawid
 *
 */
public interface GenericDao<T, ID extends Serializable> {
	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T findById(ID id);
	List<T> findAll();
	void flush();
}
