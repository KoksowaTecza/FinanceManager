package com.project.dao;

import java.util.Date;
import java.util.List;

import com.project.commons.dao.GenericDao;
import com.project.domain.ExpenseEntity;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;

public interface ExpenseDao extends GenericDao<ExpenseEntity, Integer> {
	public List<TransactionPeriodSummary> getExpenseForPeriod(String username, Date startPeriod, Date endPeriod);
}
