package com.mvc.demo.services.impl;

import com.mvc.demo.dao.SpitterDao;
import com.mvc.demo.domain.Spitter;
import com.mvc.demo.services.SpitterService;

public class SpiterServiceImpl implements SpitterService {
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
}
