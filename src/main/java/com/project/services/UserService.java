package com.project.services;

import com.project.domain.UserAccount;

public interface UserService {
	
	public boolean createUserAccount(UserAccount user);
	public UserAccount getUserByUsername(String username);
	public UserAccount updateUserAccount(UserAccount user);
}
