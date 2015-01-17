package com.project.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.CategoryExpensesDao;
import com.project.dao.CategoryRevenueDao;
import com.project.domain.CategoryExpensesEntity;
import com.project.domain.CategoryRevenueEntity;

public class CategoryExpensesJpaDao extends GenericJpaDao<CategoryExpensesEntity, String> implements CategoryExpensesDao {
	public CategoryExpensesJpaDao() {
		super(CategoryExpensesEntity.class);
	}
	
	@Override
	public List<CategoryExpensesEntity> getAllCategoryRevenuesForUser(
			String username) {
		
		List<CategoryExpensesEntity> enities;
		Assert.notNull(username);
		
		Query query = getEntityManager().createQuery("from "+ getPeristenceClass().getSimpleName() + " x where x.username = :username").setParameter("username", username);
		try {
			enities = (List<CategoryExpensesEntity>)query.getResultList();
		}catch(NoResultException e){
			return null;
		}
		return enities;
	}

	@Override
	public Integer getCategoryIdByName(String name) {
		Assert.notNull(name);
		Integer id;
		Query query = getEntityManager().createQuery("select x.id from "+ getPeristenceClass().getSimpleName() + " x where x.category_name = :category_name").setParameter("category_name", name);
		try {
			id = (Integer) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return id;
	}

	@Override
	public CategoryExpensesEntity getCategoryByName(String name) {
		Assert.notNull(name);
		CategoryExpensesEntity entity;
		Query query = getEntityManager().createQuery("from "+ getPeristenceClass().getSimpleName() + " x where x.category_name = :category_name").setParameter("category_name", name);
		try {
			entity = (CategoryExpensesEntity)query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return entity;
	}

	@Override
	public boolean daleteRevenueCategoryByName(String name) {
		Assert.notNull(name);
		Query query = getEntityManager().createQuery("delete from "+ getPeristenceClass().getSimpleName() + " x where x.category_name = :category_name").setParameter("category_name", name);
		try {
			query.executeUpdate();
		}catch(NoResultException e){
			return false;
		}
		return true;
	}
}
