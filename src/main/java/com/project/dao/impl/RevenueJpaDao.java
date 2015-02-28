package com.project.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.CategoryRevenueDao;
import com.project.dao.RevenueDao;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;
import com.project.domain.UserAccount;

public class RevenueJpaDao extends GenericJpaDao<RevenueEntity, Integer> implements RevenueDao {
	public RevenueJpaDao() {
		super(RevenueEntity.class);
	}

	@Override
	public List<TransactionPeriodSummary> getRevenueForPeriod(String username,
			Date startPeriod, Date endPeriod) {
		List<TransactionPeriodSummary> list = null;
		Assert.notNull(username);
		String querystring ="select u.category_name_id, u2.category_name, SUM(u.amount) "
				+ "from user_revenues u inner join USER_CATEGORIES_REVENUES u2 on u.category_name_id = u2.id"
				+ " where u.username = :username and u.transaction_time BETWEEN :period_start and :period_end GROUP BY (u.category_name_id, u2.category_name)";
		
		
		Query query = getEntityManager().createNativeQuery(querystring).setParameter("username", username).setParameter("period_start", startPeriod).setParameter("period_end", endPeriod);
		try {
			List<Object[]> rawList = query.getResultList();
			list = new ArrayList<TransactionPeriodSummary>();
			for(Object[] obj:rawList){
				TransactionPeriodSummary revenue = new TransactionPeriodSummary();
				revenue.setCat_id((BigDecimal)obj[0]);
				revenue.setCat_name((String)obj[1]);
				revenue.setSum((BigDecimal)obj[2]);
				list.add(revenue);
			}
		}catch(NoResultException e){
			//do nothing
		}
		return list;
	}
}
