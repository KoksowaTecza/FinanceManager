package com.mvc.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mvc.demo.dao.SpitterDao;
import com.mvc.demo.domain.Spitter;
import com.mvc.demo.services.SpitterService;

public class SpiterServiceImpl implements SpitterService, UserDetailsService {
	SpitterDao spitterDao;
	
	public boolean createSpitter(Spitter spitter){
		spitterDao.save(spitter);
		return true;
	}

	public SpitterDao getSpitterDao() {
		return spitterDao;
	}

	public void setSpitterDao(SpitterDao spitterDao) {
		this.spitterDao = spitterDao;
	}
	
	public Spitter getSpitterByUsername(String username){
		Spitter spitter = spitterDao.getSpitterByUsername(username);
		return spitter;
	}
	
	public Spitter updateSpitter(Spitter entity){
		Spitter spitter = spitterDao.update(entity);
		return spitter;
		
	}

	/**
	 * construct userDetails instance required by spring security
	 */
	public UserDetails loadUserByUsername(String username){
		Spitter spitter = spitterDao.getSpitterByUsername(username);
		
		if(spitter == null ){
			throw new UsernameNotFoundException("User for username: "+username+" not found");
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
		
		return new User(spitter.getUsername(), spitter.getPassword(), authorities);
	}
}
