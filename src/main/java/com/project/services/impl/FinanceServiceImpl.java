package com.project.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.project.dao.BalanceDao;
import com.project.dao.CategoryRevenueDao;
import com.project.dao.ExpenseDao;
import com.project.dao.RevenueDao;
import com.project.domain.BalanceEntity;
import com.project.domain.BalanceSessionObject;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ExpenseEntity;
import com.project.domain.RevenueEntity;
import com.project.domain.UserSessionObject;
import com.project.services.FinanceService;

public class FinanceServiceImpl implements FinanceService {
	BalanceDao balanceDao;
	UserSessionObject userSessionObject;
	CategoryRevenueDao categoryRevenueDao;
	RevenueDao revenueDao;
	ExpenseDao expenseDao;
	BalanceSessionObject balanceSessionObject;
	
	@Override
	public boolean isMonitorStart() {
		return balanceDao.isMonitorStart(userSessionObject.getUsername());
	}

	public BalanceDao getBalanceDao() {
		return balanceDao;
	}

	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	public UserSessionObject getUserSessionObject() {
		return userSessionObject;
	}

	public void setUserSessionObject(UserSessionObject userSessionObject) {
		this.userSessionObject = userSessionObject;
	}


	public CategoryRevenueDao getCategoryRevenueDao() {
		return categoryRevenueDao;
	}

	public void setCategoryRevenueDao(CategoryRevenueDao categoryRevenueDao) {
		this.categoryRevenueDao = categoryRevenueDao;
	}
	

	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	public RevenueDao getRevenueDao() {
		return revenueDao;
	}

	public void setRevenueDao(RevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}

	@Override
	public BalanceEntity getCurrentPeriodBalance(String username) {
		BalanceEntity balanceEntity = balanceDao.getCurrentPeriodBalance(username);
		return balanceEntity;
	}

	public BalanceSessionObject getBalanceSessionObject() {
		return balanceSessionObject;
	}

	public void setBalanceSessionObject(BalanceSessionObject balanceSessionObject) {
		this.balanceSessionObject = balanceSessionObject;
	}

	@Override
	public boolean createNewPeriod(BalanceEntity balanceEntity) {
		balanceDao.save(balanceEntity);
		return false;
	}

	@Override
	public RevenueEntity addNewRevenue(RevenueEntity revenueEntity) {
		RevenueEntity entity = revenueDao.save(revenueEntity);
		balanceDao.saveIncome(revenueEntity.getUsername(), revenueEntity.getAmount());
		BalanceEntity balanceEnt = balanceDao.getCurrentPeriodBalance(revenueEntity.getUsername());
		balanceSessionObject.setIncome(balanceEnt.getIncome().toString());
		balanceSessionObject.setBalance(getBalance(revenueEntity.getUsername()).toString());
		return entity;
	}

	@Override
	public ExpenseEntity addNewExpense(ExpenseEntity expenseEntity) {
		ExpenseEntity entity = expenseDao.save(expenseEntity);
		balanceDao.saveExpense(expenseEntity.getUsername(), expenseEntity.getAmount());
		BalanceEntity balanceEnt = balanceDao.getCurrentPeriodBalance(expenseEntity.getUsername());
		balanceSessionObject.setExpenditure(balanceEnt.getExpenditure().toString());
		balanceSessionObject.setBalance(getBalance(expenseEntity.getUsername()).toString());
		return entity;
	}

	@Override
	public BigDecimal getBalance(String username) {
		return balanceDao.getBalance(username);
	}


}
