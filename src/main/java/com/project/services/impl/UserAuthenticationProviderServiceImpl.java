package com.project.services.impl;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.domain.ProfileImage;
import com.project.domain.UserAccount;
import com.project.domain.UserSessionObject;
import com.project.services.ImageService;
import com.project.services.UserService;
import com.project.services.UserAuthenticationProviderService;



/**
 * Provides processing service to set user authetication sssion
 *  
 * @author Dawid
 */
public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {
	
	private AuthenticationManager authenticationManager; 
	private UserSessionObject userSessionObject;
	private UserService userService;
	private ImageService imageService;
	
	
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
			userSessionObject.setUserFullName(user.getUserFullName());
			userSessionObject.setUsername(user.getUsername());
			userSessionObject.setPassword(user.getPassword());
			userSessionObject.setEmail(user.getEmail());
			userSessionObject.setProfile_image_name(user.getProfile_image_name());
			userSessionObject.setId(user.getId());
			
			ProfileImage image = imageService.getUserProfileImage(user.getProfile_image_name());
			userSessionObject.setProfile_image(image.getProfile_image());
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

	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserSessionObject(UserSessionObject userSessionObject) {
		this.userSessionObject = userSessionObject;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	

}
