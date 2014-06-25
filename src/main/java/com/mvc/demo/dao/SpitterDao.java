package com.mvc.demo.dao;

import com.mvc.demo.commons.dao.GenericDao;
import com.mvc.demo.domain.Spitter;

public interface SpitterDao extends GenericDao<Spitter, Long> {
	public Spitter getSpitterByUsername(String username);
}
