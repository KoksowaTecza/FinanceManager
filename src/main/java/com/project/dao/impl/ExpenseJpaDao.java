package com.project.dao.impl;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.ExpenseDao;
import com.project.dao.RevenueDao;
import com.project.domain.ExpenseEntity;
import com.project.domain.RevenueEntity;

public class ExpenseJpaDao extends GenericJpaDao<ExpenseEntity, Integer> implements ExpenseDao {
	public ExpenseJpaDao() {
		super(ExpenseEntity.class);
	}
}
