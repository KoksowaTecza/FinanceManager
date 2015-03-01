package com.project.dao;

import java.util.List;

import com.project.commons.dao.GenericDao;
import com.project.domain.CategoryExpensesEntity;
import com.project.domain.CategoryRevenueEntity;

public interface CategoryExpensesDao extends GenericDao<CategoryExpensesEntity, String>{
	public List<CategoryExpensesEntity> getAllCategoryRevenuesForUser(String username);
	public Long getCategoryIdByName(String name);
	public CategoryExpensesEntity getCategoryByName(String name);
	public boolean daleteRevenueCategoryByName(String name);
}
