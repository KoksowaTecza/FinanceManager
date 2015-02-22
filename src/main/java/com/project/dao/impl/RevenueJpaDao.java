package com.project.dao.impl;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.CategoryRevenueDao;
import com.project.dao.RevenueDao;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.RevenueEntity;

public class RevenueJpaDao extends GenericJpaDao<RevenueEntity, Integer> implements RevenueDao {
	public RevenueJpaDao() {
		super(RevenueEntity.class);
	}
}
