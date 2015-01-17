package com.project.services.impl;

import java.util.List;

import com.project.dao.CategoryExpensesDao;
import com.project.dao.CategoryRevenueDao;
import com.project.domain.CategoryExpensesEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryRevenueDao categoryRevenueDao;
	CategoryExpensesDao categoryExpensesDao;
	public CategoryRevenueDao getCategoryRevenueDao() {
		return categoryRevenueDao;
	}

	public void setCategoryRevenueDao(CategoryRevenueDao categoryRevenueDao) {
		this.categoryRevenueDao = categoryRevenueDao;
	}

	public CategoryExpensesDao getCategoryExpensesDao() {
		return categoryExpensesDao;
	}

	public void setCategoryExpensesDao(CategoryExpensesDao categoryExpensesDao) {
		this.categoryExpensesDao = categoryExpensesDao;
	}


	@Override
	public CategoryRevenueEntity careateNewRevenueCategory(
			CategoryRevenueEntity categoryRevenueEntity) {
		return categoryRevenueDao.save(categoryRevenueEntity);
	}

	@Override
	public CategoryRevenueEntity getCategoryRevenueEntity(String categoryName) {
		return categoryRevenueDao.getCategoryByName(categoryName);
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

	@Override
	public Integer getCategoryRevenueIdByName(String name) {
		return categoryRevenueDao.getCategoryIdByName(name);
	}

	@Override
	public boolean daleteRevenueCategoryByName(String name) {
		return categoryRevenueDao.daleteRevenueCategoryByName(name);
	}
	/////////////////////////
	
	@Override
	public CategoryExpensesEntity careateNewExpensesCategory(
			CategoryExpensesEntity categoryExpensesEntity) {
		return categoryExpensesDao.save(categoryExpensesEntity);
	}

	@Override
	public CategoryExpensesEntity getCategoryExpensesEntity(String categoryName) {
		return categoryExpensesDao.getCategoryByName(categoryName);
	}

	@Override
	public CategoryExpensesEntity updateCategoryExpensesEntity(
			CategoryExpensesEntity categoryExpensesEntity) {
		return categoryExpensesDao.update(categoryExpensesEntity);
	}

	@Override
	public List<CategoryExpensesEntity> getAllCategoryExpensesForUser(
			String username) {
		return categoryExpensesDao.getAllCategoryRevenuesForUser(username);
	}

	@Override
	public Integer getCategoryExpensesIdByName(String name) {
		return categoryExpensesDao.getCategoryIdByName(name);
	}

	@Override
	public boolean daleteExpensesCategoryByName(String name) {
		return categoryExpensesDao.daleteRevenueCategoryByName(name);
	}
	
	

}
