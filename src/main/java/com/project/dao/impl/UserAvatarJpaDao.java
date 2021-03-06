package com.project.dao.impl;

import java.sql.Blob;

import org.hibernate.Session;

import com.project.commons.dao.GenericJpaDao;
import com.project.dao.UserAvatarDao;
import com.project.domain.ProfileImage;
import com.project.domain.UserAccount;

public class UserAvatarJpaDao extends GenericJpaDao<ProfileImage, String> implements UserAvatarDao{
	public UserAvatarJpaDao() {
		super(ProfileImage.class);
	}
}
