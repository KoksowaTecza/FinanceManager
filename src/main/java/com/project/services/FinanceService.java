package com.project.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.domain.BalanceEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;

public interface FinanceService {
	boolean isMonitorStart();
	BalanceEntity getCurrentPeriodBalance(String username);
	boolean createNewPeriod(BalanceEntity balanceEntity);
	RevenueEntity addNewRevenue(RevenueEntity revenueEntity);
	ExpenseEntity addNewExpense(ExpenseEntity revenueEntity);
	public BigDecimal getBalance(String username);
	public List<TransactionPeriodSummary> getRevenouSummaryForPeriod(String username, Date startPeriod, Date endPeriod);
	public List<TransactionPeriodSummary> getRevenouSummaryForCurrentPeriod(String username);
	public List<TransactionPeriodSummary> getExpenseSummaryForPeriod(String username, Date startPeriod, Date endPeriod);
	public List<TransactionPeriodSummary> getExpenseSummaryForCurrentPeriod(String username);
	public ExpenseProjection saveExpenseProjection(ExpenseProjection expenseProjection);
}
