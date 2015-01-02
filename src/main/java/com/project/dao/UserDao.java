package com.project.dao;

import java.sql.Blob;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.project.commons.dao.GenericDao;
import com.project.domain.UserAccount;

public interface UserDao extends GenericDao<UserAccount, Long> {
	public UserAccount getUserByUsername(String username);
	public Session getSessionObject();
}
