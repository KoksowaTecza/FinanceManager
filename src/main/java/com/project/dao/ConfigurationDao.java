package com.project.dao;

import com.project.commons.dao.GenericDao;
import com.project.domain.ConfigurationData;
import com.project.domain.UserAccount;

public interface ConfigurationDao extends GenericDao<ConfigurationData, String> {
	public boolean checkIfConfigurationExsist(String username);
}
