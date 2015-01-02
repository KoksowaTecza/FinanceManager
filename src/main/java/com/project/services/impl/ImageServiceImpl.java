package com.project.services.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.project.dao.UserAvatarDao;
import com.project.domain.ProfileImage;
import com.project.services.ImageService;

public class ImageServiceImpl implements ImageService {
	private UserAvatarDao userAvatarDao;
	private int avatarWidth = 140;
	private int avatarHeight = 160;

	public BufferedImage resizeUserAvatar(BufferedImage avatar) {
		int type = avatar.getType() == 0? BufferedImage.TYPE_INT_ARGB : avatar.getType();
		BufferedImage resizedImage = new BufferedImage(avatarWidth, avatarHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(avatar, 0, 0, avatarWidth, avatarHeight, null);
		g.dispose();
		return resizedImage;
	}

	@Override
	public ProfileImage getUserProfileImage(String username) {
		ProfileImage image = userAvatarDao.findById(username);
		return image;
	}

	@Override
	public ProfileImage saveProfileImage(ProfileImage image) {
		ProfileImage profileImage = userAvatarDao.save(image);
		return profileImage;
	}

	public void setUserAvatarDao(UserAvatarDao userAvatarDao) {
		this.userAvatarDao = userAvatarDao;
	}

}
