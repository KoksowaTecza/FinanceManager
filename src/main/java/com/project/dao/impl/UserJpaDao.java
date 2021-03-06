package com.project.dao.impl;

import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.UserDao;
import com.project.domain.UserAccount;


public class UserJpaDao extends GenericJpaDao<UserAccount, Long> implements UserDao{

	public UserJpaDao() {
		super(UserAccount.class);
	}

	public UserAccount getUserByUsername(String username) {
		Assert.notNull(username);
		
		UserAccount user = null;
		
		Query query = getEntityManager().createQuery("from " + getPeristenceClass().getName() + " u where u.username = :username").setParameter("username", username);
		
		try {
			user = (UserAccount)query.getSingleResult();
		}catch(NoResultException e){
			//do nothing
		}
		return user;
	}

	@Override
	public Session getSessionObject() {
		Session session = getEntityManager().unwrap(Session.class);
		return session;
	}

	
	

}
