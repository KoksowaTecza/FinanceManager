package com.project.dao;

import com.project.commons.dao.GenericDao;
import com.project.domain.UserAccount;

public interface UserDao extends GenericDao<UserAccount, Long> {
	public UserAccount getUserByUsername(String username);
}
