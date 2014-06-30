package com.mvc.demo.services.impl;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mvc.demo.domain.Spitter;
import com.mvc.demo.services.SpitterService;
import com.mvc.demo.services.UserAuthenticationProviderService;



/**
 * Provides processing service to set user authetication sssion
 *  
 * @author Dawid
 */
public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {
	
	private AuthenticationManager authenticationManager; 
	private Spitter spitterEntity;
	private SpitterService spitterService;
	
	
	/**
	 * Process user authentication
	 * 
	 * @param user
	 * @return
	 */
	public boolean processUserAuthentication(String username, String password) {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(username, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			//set spitter session scope entity
			Spitter spitter = spitterService.getSpitterByUsername(username);
			spitterEntity.setUserFullName(spitter.getUserFullName());
			spitterEntity.setUsername(spitter.getUsername());
			spitterEntity.setPassword(spitter.getPassword());
			spitterEntity.setEmail(spitter.getEmail());
			spitterEntity.setProfile_image(spitter.getProfile_image());
			spitterEntity.setId(spitter.getId());
			return true;
		}catch(AuthenticationException e) {
			//TO DO set exception
			return false;
		}
	}
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setSpitterEntity(Spitter spitterEntity) {
		this.spitterEntity = spitterEntity;
	}

	public void setSpitterService(SpitterService spitterService) {
		this.spitterService = spitterService;
	}
	
	

}
