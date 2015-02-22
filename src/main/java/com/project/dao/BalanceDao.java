package com.project.dao;

import java.math.BigDecimal;

import com.project.commons.dao.GenericDao;
import com.project.domain.BalanceEntity;
import com.project.domain.ConfigurationData;

public interface BalanceDao extends GenericDao<BalanceEntity, Long> {
	Integer getBalanceIdByUsername(String username);
	boolean isMonitorStart(String username);
	public BalanceEntity getCurrentPeriodBalance(String username);
	public boolean saveIncome(String username, double amount);
	public boolean saveExpense(String username, double amount);
}
