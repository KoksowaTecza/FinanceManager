package com.mvc.demo.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.mvc.demo.commons.dao.GenericJpaDao;
import com.mvc.demo.domain.Spitter;


public class SpitterJpaDao extends GenericJpaDao<Spitter, Long> implements SpitterDao{

	public SpitterJpaDao() {
		super(Spitter.class);
	}

	public Spitter getSpitterByUsername(String username) {
		Assert.notNull(username);
		
		Spitter spitter = null;
		
		Query query = getEntityManager().createQuery("select u from " + getPeristenceClass().getName() + " u where u.username = :username").setParameter("username", username);
		
		try {
			spitter = (Spitter)query.getSingleResult();
		}catch(NoResultException e){
			//do nothing
		}
		return spitter;
	}
	
	
	

}
