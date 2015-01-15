package com.project.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.project.commons.dao.GenericDao;
import com.project.commons.dao.GenericJpaDao;
import com.project.dao.ConfigurationDao;
import com.project.domain.ConfigurationData;
import com.project.domain.UserAccount;

public class ConfigurationJpaDao extends GenericJpaDao<ConfigurationData, String> implements ConfigurationDao{

	public ConfigurationJpaDao() {
		super(ConfigurationData.class);
	}

	@Override
	public boolean checkIfConfigurationExsist(String username) {
		Assert.notNull(username);
		
		Query query = getEntityManager().createQuery("from "+ getPeristenceClass().getSimpleName() + " x where x.username = :username").setParameter("username", username);
		try {
			query.getSingleResult();
		}catch(NoResultException e){
			return false;
		}
		return true;
	}

}
