package com.project.services;

import java.sql.Blob;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.UserAccount;

public interface UserService {
	
	public boolean createUserAccount(UserAccount user);
	public UserAccount getUserByUsername(String username);
	public UserAccount updateUserAccount(UserAccount user);
	public Session getSessionObject();
	public Blob getUserAvatar(String username);
}
