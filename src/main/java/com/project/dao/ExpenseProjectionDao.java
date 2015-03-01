package com.project.dao;

import java.util.List;

import com.project.commons.dao.GenericDao;
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;

public interface ExpenseProjectionDao extends GenericDao<ExpenseProjection, Integer> {
	public Long checkIfProjectionExist(Long balanceId, Long categoryId);
	public List<ExpenseProjection> getProjectionForPeriod(Long balanceId);
}
