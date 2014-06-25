package com.mvc.demo.commons.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.commons.domain.BaseEntity;

/**
 * provide generic common implementaions of genericDao interface perositence methos
 * Extends this abstract class to implement Dao of your specific needs
 * 
 * @author Dawid
 *
 */
@Transactional
public abstract class GenericJpaDao<T, ID extends Serializable> implements GenericDao<T, ID> {
	private Class<T> peristenceClass;
	private EntityManager entityManager;
	
	public GenericJpaDao(Class<T> peristenceClass){
		this.peristenceClass = peristenceClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getPeristenceClass() {
		return peristenceClass;
	}

	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	public T update(T entity) {
		T mergedEntity = getEntityManager().merge(entity);
		return mergedEntity;
	}

	public void delete(T entity) {
		if(BaseEntity.class.isAssignableFrom(peristenceClass)){
			getEntityManager().remove(
					getEntityManager().getReference(entity.getClass(), ((BaseEntity)entity).getId()));
		}else {
			entity = getEntityManager().merge(entity);
			getEntityManager().remove(entity);
		}
		
	}

	@Transactional(readOnly=true)
	public T findById(ID id) {
		T entity = (T) getEntityManager().find(getPeristenceClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<T> findAll() {
		return getEntityManager().createQuery("select x from "+ getPeristenceClass().getSimpleName() + " x").getResultList();
	}

	public void flush() {
		getEntityManager().flush();
		
	}

	
	
}
