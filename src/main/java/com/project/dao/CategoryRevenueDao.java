package com.project.dao;

import java.util.Date;
import java.util.List;

import com.project.commons.dao.GenericDao;
import com.project.domain.BalanceEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.TransactionPeriodSummary;

public interface CategoryRevenueDao extends GenericDao<CategoryRevenueEntity, String> {
	public List<CategoryRevenueEntity> getAllCategoryRevenuesForUser(String username);
	public Long getCategoryIdByName(String name);
	public CategoryRevenueEntity getCategoryByName(String name);
	public boolean daleteRevenueCategoryByName(String name);
}
