package com.project.services;

import java.util.List;

import com.project.domain.CategoryExpensesEntity;
import com.project.domain.CategoryRevenueEntity;

public interface CategoryService {
	CategoryRevenueEntity careateNewRevenueCategory(CategoryRevenueEntity categoryRevenueEntity);
	CategoryRevenueEntity getCategoryRevenueEntity(String categoryName);
	CategoryRevenueEntity updateCategoryRevenueEntity(CategoryRevenueEntity categoryRevenueEntity);
	public List<CategoryRevenueEntity> getAllCategoryRevenuesForUser(String username);
	public Integer getCategoryRevenueIdByName(String name);
	public boolean daleteRevenueCategoryByName(String name);
	
	CategoryExpensesEntity careateNewExpensesCategory(CategoryExpensesEntity categoryRevenueEntity);
	CategoryExpensesEntity getCategoryExpensesEntity(String categoryName);
	CategoryExpensesEntity updateCategoryExpensesEntity(CategoryExpensesEntity categoryRevenueEntity);
	public List<CategoryExpensesEntity> getAllCategoryExpensesForUser(String username);
	public Integer getCategoryExpensesIdByName(String name);
	public boolean daleteExpensesCategoryByName(String name);
}
