package com.project.services.impl;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.domain.BalanceEntity;
import com.project.domain.BalanceSessionObject;
import com.project.domain.ProfileImage;
import com.project.domain.UserAccount;
import com.project.domain.UserSessionObject;
import com.project.services.ConfigurationDataService;
import com.project.services.FinanceService;
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
	private ConfigurationDataService configurationDataService;
	private FinanceService financeService;
	private BalanceSessionObject balanceSessionObject;
	
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
			userSessionObject.setConfigurationAllert(!configurationDataService.checkIfConfigurationExsist(username));
			ProfileImage image = imageService.getUserProfileImage(user.getProfile_image_name());
			userSessionObject.setProfile_image(image.getProfile_image());
			userSessionObject.setMonitorAllert(financeService.isMonitorStart());
			
			//setBalanceSessionObject
			BalanceEntity balanceEntity = financeService.getCurrentPeriodBalance(username);
			balanceSessionObject.setBalance(financeService.getBalance(username) == null?"0":financeService.getBalance(username).toString());
			balanceSessionObject.setExpenditure(balanceEntity.getExpenditure() == null? "0":balanceEntity.getExpenditure().toString() );
			balanceSessionObject.setIncome(balanceEntity.getIncome() == null?"0":balanceEntity.getIncome().toString());
			String date = "Nie ustalono!";
			if(balanceEntity.getPeriod_start() != null){
				SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
				Date periodStart =  balanceEntity.getPeriod_start();
				date =  ft.format(periodStart);
			}
			balanceSessionObject.setPeriod_start(date);
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			Date today =  Calendar.getInstance().getTime(); 
			balanceSessionObject.setToday(ft.format(today));
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

	public ImageService getImageService() {
		return imageService;
	}

	public void setConfigurationDataService(
			ConfigurationDataService configurationDataService) {
		this.configurationDataService = configurationDataService;
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	public void setBalanceSessionObject(BalanceSessionObject balanceSessionObject) {
		this.balanceSessionObject = balanceSessionObject;
	}
	
	

}
