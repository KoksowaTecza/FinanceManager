package com.mvc.demo.services;

import com.mvc.demo.domain.Spitter;

public interface SpitterService {
	
	public boolean createSpitter(Spitter spitter);
	public Spitter getSpitterByUsername(String username);
	public Spitter updateSpitter(Spitter spitter);
}
