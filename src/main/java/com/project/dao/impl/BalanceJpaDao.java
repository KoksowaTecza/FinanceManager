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
	public BalanceEntity getCurrentPeriodBalance(String username) {
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

	@Override
	public boolean saveIncome(String username, double amount) {
		Assert.notNull(username);
		BalanceEntity balanceEntity = getCurrentPeriodBalance(username);
		BigDecimal income = balanceEntity.getIncome();
		if(income != null){
			income = income.add(new BigDecimal(Double.toString(amount)));
		}else {
			income = new BigDecimal(Double.toString(amount));
		}
		int id = balanceEntity.getId();
		String name = getPeristenceClass().getName();
		Query query = getEntityManager().createQuery("update "+name+" u set u.income = :income where u.id = :id").setParameter("income", income).setParameter("id", id);
		
		int result = query.executeUpdate();
		if(result > 0 )return true;
		return false;
	}

	@Override
	public boolean saveExpense(String username, double amount) {
		Assert.notNull(username);
		BalanceEntity balanceEntity = getCurrentPeriodBalance(username);
		BigDecimal expense = balanceEntity.getExpenditure();
		if(expense != null){
			expense = expense.add(new BigDecimal(Double.toString(amount)));
		}else {
			expense = new BigDecimal(Double.toString(amount));
		}
		int id = balanceEntity.getId();
		String name = getPeristenceClass().getName();
		Query query = getEntityManager().createQuery("update "+name+" u set u.expenditure = :expense where u.id = :id").setParameter("expense", expense).setParameter("id", id);
		
		int result = query.executeUpdate();
		if(result > 0 )return true;
		return false;
	}

	@Override
	public BigDecimal getBalance(String username) {
		BalanceEntity balanceEnt = getCurrentPeriodBalance(username);
		BigDecimal result = new BigDecimal("0");
		BigDecimal income = balanceEnt.getIncome();
		BigDecimal epenses = balanceEnt.getExpenditure().negate();
		BigDecimal previousPeriod = balanceEnt.getBalance();
		
		result = result.add(income == null?new BigDecimal("0"):income).add(previousPeriod == null?new BigDecimal("0"):previousPeriod).add(epenses == null ? new BigDecimal("0"): epenses);
		
		return result;
	}

}
