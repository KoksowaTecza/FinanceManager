package com.project.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.ExpenseDao;
import com.project.dao.ExpenseProjectionDao;
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;

public class ExpenseProjectionJpaDao extends GenericJpaDao<ExpenseProjection, Integer> implements ExpenseProjectionDao{
	public ExpenseProjectionJpaDao() {
		super(ExpenseProjection.class);
	}

	@Override
	public Long checkIfProjectionExist(Long balanceId, Long categoryId) {
		Assert.notNull(balanceId);
		Assert.notNull(categoryId);
		Long id = null;
		Query query = getEntityManager().createQuery("select u.id from " + getPeristenceClass().getName() + " u where u.category_name_id = :category_name_id and u.user_balance_id = :user_balance_id ").setParameter("category_name_id", categoryId).setParameter("user_balance_id", balanceId);
		
		try {
			id = (Long)query.getSingleResult();
		}catch(NoResultException e){
			
		}
		return id;
	}


	@Override
	public List<ExpenseProjection> getProjectionForPeriod(Long balanceId) {
		Assert.notNull(balanceId);
		List<ExpenseProjection> list = null;
		Query query = getEntityManager().createQuery("from " + getPeristenceClass().getName() + " u where u.user_balance_id = :user_balance_id ").setParameter("user_balance_id", balanceId);
		try {
			list = (List<ExpenseProjection>)query.getResultList();
		}catch(NoResultException e){
			
		}
		return list;
	}
}
