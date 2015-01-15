package com.project.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.project.dao.BalanceDao;
import com.project.dao.CategoryRevenueDao;
import com.project.domain.BalanceEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.UserSessionObject;
import com.project.services.FinanceService;

public class FinanceServiceImpl implements FinanceService {
	BalanceDao balanceDao;
	UserSessionObject userSessionObject;
	CategoryRevenueDao categoryRevenueDao;
	
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
	

	@Override
	public BalanceEntity getPreviousPeriodBalance(String username) {
		BalanceEntity balanceEntity = balanceDao.getPreviousPeriodBalance(username);
		return balanceEntity;
	}

	@Override
	public boolean createNewPeriod(BalanceEntity balanceEntity) {
		balanceDao.save(balanceEntity);
		return false;
	}

	@Override
	public CategoryRevenueEntity careateNewRevenueCategory(
			CategoryRevenueEntity categoryRevenueEntity) {
		return categoryRevenueDao.save(categoryRevenueEntity);
	}

	@Override
	public CategoryRevenueEntity getcategoryRevenueEntity(String categoryName) {
		return categoryRevenueDao.findById(categoryName);
	}

	@Override
	public CategoryRevenueEntity updateCategoryRevenueEntity(
			CategoryRevenueEntity categoryRevenueEntity) {
		return categoryRevenueDao.update(categoryRevenueEntity);
	}

	@Override
	public List<CategoryRevenueEntity> getAllCategoryRevenuesForUser(
			String username) {
		return categoryRevenueDao.getAllCategoryRevenuesForUser(username);
	}

}
