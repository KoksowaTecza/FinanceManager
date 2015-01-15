package com.project.services;

import com.project.domain.BalanceEntity;
import com.project.domain.ConfigurationData;

public interface ConfigurationDataService {
	public boolean saveConfigurationData(ConfigurationData configurationData, BalanceEntity balanceEntity);
	public ConfigurationData getConfigurationDataByUsername(String username);
	public boolean updateConfigurationData(ConfigurationData configurationData, BalanceEntity balanceEntity);
	public boolean checkIfConfigurationExsist(String username);
}
