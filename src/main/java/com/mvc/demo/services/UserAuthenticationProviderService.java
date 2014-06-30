package com.mvc.demo.services;

import com.mvc.demo.domain.Spitter;



/**
 * Provides processing service to set user authetication session
 *  
 * @author Dawid
 */
public interface UserAuthenticationProviderService {
	/**
	 * Process user authentication
	 * 
	 * @param user
	 * @return
	 */
	boolean processUserAuthentication(String username, String password);
}
