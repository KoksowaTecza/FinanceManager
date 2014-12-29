package com.project.services.impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.project.dao.UserDao;
import com.project.domain.UserAccount;
import com.project.services.UserService;

public class UserServiceImpl implements UserService, UserDetailsService {
	UserDao userDao;
	
	public boolean createUserAccount(UserAccount user){
		userDao.save(user);
		return true;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserAccount getUserByUsername(String username){
		UserAccount user = userDao.getUserByUsername(username);
		return user;
	}
	
	public UserAccount updateUserAccount(UserAccount entity){
		UserAccount user = userDao.update(entity);
		return user;
		
	}

	/**
	 * construct userDetails instance required by spring security
	 */
	public UserDetails loadUserByUsername(String username){
		UserAccount user = userDao.getUserByUsername(username);
		
		if(user == null ){
			throw new UsernameNotFoundException("User for username: "+username+" not found");
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	public Session getSessionObject(){
		return userDao.getSessionObject();
	}

	@Override
	public Blob getUserAvatar(String username) {
		return userDao.getUserAvatar(username);
	}
}
