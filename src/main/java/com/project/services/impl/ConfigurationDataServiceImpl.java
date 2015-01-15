package com.project.services.impl;

import com.project.dao.BalanceDao;
import com.project.dao.ConfigurationDao;
import com.project.domain.BalanceEntity;
import com.project.domain.ConfigurationData;
import com.project.domain.UserSessionObject;
import com.project.services.ConfigurationDataService;

public class ConfigurationDataServiceImpl implements ConfigurationDataService {
	private ConfigurationDao configurationDao;
	private BalanceDao balanceDao;
	private UserSessionObject userSessionObject;

	@Override
	public boolean saveConfigurationData(ConfigurationData configurationData, BalanceEntity balanceEntity) {
		configurationDao.save(configurationData);
		balanceDao.save(balanceEntity);
		userSessionObject.setConfigurationAllert(false);
		return true;
	}

	public ConfigurationDao getConfigurationDao() {
		return configurationDao;
	}

	public void setConfigurationDao(ConfigurationDao configurationDao) {
		this.configurationDao = configurationDao;
	}

	@Override
	public ConfigurationData getConfigurationDataByUsername(String username) {
		ConfigurationData configurationData = configurationDao.findById(username);
		return configurationData;
	}

	@Override
	public boolean updateConfigurationData(ConfigurationData configurationData, BalanceEntity balanceEntity) {
		configurationDao.update(configurationData);
		Integer id = balanceDao.getBalanceIdByUsername(balanceEntity.getUsername());
		balanceEntity.setId(id);
		balanceDao.update(balanceEntity);
		return true;
	}

	public BalanceDao getBalanceDao() {
		return balanceDao;
	}

	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	@Override
	public boolean checkIfConfigurationExsist(String username) {
		return configurationDao.checkIfConfigurationExsist(username);
	}

	public void setUserSessionObject(UserSessionObject userSessionObject) {
		this.userSessionObject = userSessionObject;
	}
	
}
