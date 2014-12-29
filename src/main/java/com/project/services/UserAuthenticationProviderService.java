package com.project.services;

import com.project.domain.UserAccount;



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
