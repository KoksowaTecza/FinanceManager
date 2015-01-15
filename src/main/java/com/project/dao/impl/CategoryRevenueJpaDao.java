package com.project.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericDao;
import com.project.commons.dao.GenericJpaDao;
import com.project.dao.CategoryRevenueDao;
import com.project.dao.ConfigurationDao;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ConfigurationData;

public class CategoryRevenueJpaDao extends GenericJpaDao<CategoryRevenueEntity, String> implements CategoryRevenueDao {
	public CategoryRevenueJpaDao() {
		super(CategoryRevenueEntity.class);
	}

	@Override
	public List<CategoryRevenueEntity> getAllCategoryRevenuesForUser(
			String username) {
		
		List<CategoryRevenueEntity> enities;
		Assert.notNull(username);
		
		Query query = getEntityManager().createQuery("from "+ getPeristenceClass().getSimpleName() + " x where x.username = :username").setParameter("username", username);
		try {
			enities = (List<CategoryRevenueEntity>)query.getResultList();
		}catch(NoResultException e){
			return null;
		}
		return enities;
	}
}
