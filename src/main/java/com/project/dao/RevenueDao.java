package com.project.dao;

import java.util.Date;
import java.util.List;

import com.project.commons.dao.GenericDao;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;

public interface RevenueDao extends GenericDao<RevenueEntity, Integer>{
	public List<TransactionPeriodSummary> getRevenueForPeriod(String username, Date startPeriod, Date endPeriod);
}
