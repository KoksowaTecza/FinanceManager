package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import com.project.domain.BalanceEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ExpenseEntity;
import com.project.domain.RevenueEntity;

public interface FinanceService {
	boolean isMonitorStart();
	BalanceEntity getCurrentPeriodBalance(String username);
	boolean createNewPeriod(BalanceEntity balanceEntity);
	RevenueEntity addNewRevenue(RevenueEntity revenueEntity);
	ExpenseEntity addNewExpense(ExpenseEntity revenueEntity);
	public BigDecimal getBalance(String username);
}
