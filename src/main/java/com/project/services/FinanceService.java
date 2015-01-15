package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import com.project.domain.BalanceEntity;
import com.project.domain.CategoryRevenueEntity;

public interface FinanceService {
	boolean isMonitorStart();
	BalanceEntity getPreviousPeriodBalance(String username);
	boolean createNewPeriod(BalanceEntity balanceEntity);
	CategoryRevenueEntity careateNewRevenueCategory(CategoryRevenueEntity categoryRevenueEntity);
	CategoryRevenueEntity getcategoryRevenueEntity(String categoryName);
	CategoryRevenueEntity updateCategoryRevenueEntity(CategoryRevenueEntity categoryRevenueEntity);
	public List<CategoryRevenueEntity> getAllCategoryRevenuesForUser(String username);
}
