package com.project.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.project.dao.BalanceDao;
import com.project.dao.CategoryRevenueDao;
import com.project.dao.ExpenseDao;
import com.project.dao.ExpenseProjectionDao;
import com.project.dao.RevenueDao;
import com.project.domain.BalanceEntity;
import com.project.domain.BalanceSessionObject;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;
import com.project.domain.UserSessionObject;
import com.project.services.FinanceService;

public class FinanceServiceImpl implements FinanceService {
	BalanceDao balanceDao;
	UserSessionObject userSessionObject;
	CategoryRevenueDao categoryRevenueDao;
	RevenueDao revenueDao;
	ExpenseDao expenseDao;
	BalanceSessionObject balanceSessionObject;
	ExpenseProjectionDao expenseProjectionDao;
	
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

	@Override
	public List<TransactionPeriodSummary> getRevenouSummaryForPeriod(String username,
			Date startPeriod, Date endPeriod) {
		List<TransactionPeriodSummary> list = revenueDao.getRevenueForPeriod(username, startPeriod, endPeriod);
		return list;
	}
	

	@Override
	public List<TransactionPeriodSummary> getRevenouSummaryForCurrentPeriod(String username) {
		BalanceEntity balance = getCurrentPeriodBalance(username);
		Date period_start = balance.getPeriod_start();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Date period_end =  Calendar.getInstance().getTime(); 
		List<TransactionPeriodSummary> list = getRevenouSummaryForPeriod(username,
				period_start, period_end);
		return list;
	}

	@Override
	public List<TransactionPeriodSummary> getExpenseSummaryForCurrentPeriod(
			String username) {
		BalanceEntity balance = getCurrentPeriodBalance(username);
		Date period_start = balance.getPeriod_start();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Date period_end =  Calendar.getInstance().getTime(); 
		List<TransactionPeriodSummary> list = getExpenseSummaryForPeriod(username,
				period_start, period_end);
		List<ExpenseProjection> list2 = expenseProjectionDao.getProjectionForPeriod(balance.getId());
		for(TransactionPeriodSummary summary : list){
			for(ExpenseProjection projection : list2){
				if(summary.getCat_id().longValue() == projection.getCategory_name_id()){
					summary.setProjection(projection.getAmount());
				}
			}
		}
		return list;
	}

	@Override
	public List<TransactionPeriodSummary> getExpenseSummaryForPeriod(
			String username, Date startPeriod, Date endPeriod) {
		List<TransactionPeriodSummary> list = expenseDao.getExpenseForPeriod(username, startPeriod, endPeriod);
		return list;
	}

	@Override
	public ExpenseProjection saveExpenseProjection(ExpenseProjection expenseProjection) {
		Long projectionId = expenseProjectionDao.checkIfProjectionExist(expenseProjection.getUser_balance_id(), expenseProjection.getCategory_name_id());
		ExpenseProjection projection = null;
		if(projectionId == null)
			projection = expenseProjectionDao.save(expenseProjection);
		else{
			expenseProjection.setId(projectionId);
			expenseProjectionDao.update(expenseProjection);
		}
		return projection;
	}

	public ExpenseProjectionDao getExpenseProjectionDao() {
		return expenseProjectionDao;
	}

	public void setExpenseProjectionDao(ExpenseProjectionDao expenseProjectionDao) {
		this.expenseProjectionDao = expenseProjectionDao;
	}


}
