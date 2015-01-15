package com.project.dao.impl;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericDao;
import com.project.commons.dao.GenericJpaDao;
import com.project.dao.BalanceDao;
import com.project.domain.BalanceEntity;
import com.project.domain.ConfigurationData;
import com.project.domain.UserAccount;

public class BalanceJpaDao extends  GenericJpaDao<BalanceEntity, Long> implements BalanceDao {

	public BalanceJpaDao() {
		super(BalanceEntity.class);
	}

	@Override
	public Integer getBalanceIdByUsername(String username) {
		Assert.notNull(username);
		Integer id = null;
		String name = getPeristenceClass().getName();
		Query query = getEntityManager().createQuery("select u.id from " + name + " u where u.username = :username and u.period_end is null").setParameter("username", username);
		
		try {
			id = (Integer)query.getSingleResult();
		}catch(NoResultException e){
			//do nothing
		}
		return id;
	}

	@Override
	public boolean isMonitorStart(String username) {
		Assert.notNull(username);
		
		String name = getPeristenceClass().getName();
		Query query = getEntityManager().createQuery("from " + name + " u where u.username = :username and u.period_end is null and u.period_start is null").setParameter("username", username);
		
		try {
			query.getSingleResult();
		}catch(NoResultException e){
			return true;
		}
		return false;
	}

	@Override
	public BalanceEntity getPreviousPeriodBalance(String username) {
		Assert.notNull(username);
		BalanceEntity balance = null;
		
		String name = getPeristenceClass().getName();
		Query query = getEntityManager().createQuery("from " + name + " u where u.username = :username and u.period_end is null").setParameter("username", username);
		
		try {
			balance = (BalanceEntity)query.getSingleResult();
		}catch(NoResultException e){
		}
		return balance;
	}

}
