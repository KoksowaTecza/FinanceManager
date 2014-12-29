package com.project.services.impl;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.domain.UserAccount;
import com.project.services.UserService;
import com.project.services.UserAuthenticationProviderService;



/**
 * Provides processing service to set user authetication sssion
 *  
 * @author Dawid
 */
public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {
	
	private AuthenticationManager authenticationManager; 
	private UserAccount userEnitity;
	private UserService userService;
	
	
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
			UserAccount user = userService.getUserByUsername(username);
			userEnitity.setUserFullName(user.getUserFullName());
			userEnitity.setUsername(user.getUsername());
			userEnitity.setPassword(user.getPassword());
			userEnitity.setEmail(user.getEmail());
			userEnitity.setProfile_image(user.getProfile_image());
			userEnitity.setId(user.getId());
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

	public void setUserEntity(UserAccount userEnitity) {
		this.userEnitity = userEnitity;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
