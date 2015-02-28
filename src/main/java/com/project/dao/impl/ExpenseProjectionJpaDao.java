package com.project.dao.impl;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.ExpenseDao;
import com.project.dao.ExpenseProjectionDao;
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;

public class ExpenseProjectionJpaDao extends GenericJpaDao<ExpenseProjection, Integer> implements ExpenseProjectionDao{
	public ExpenseProjectionJpaDao() {
		super(ExpenseProjection.class);
	}
}
