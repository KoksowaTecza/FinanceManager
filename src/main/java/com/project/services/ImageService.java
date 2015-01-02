package com.project.services;

import java.awt.image.BufferedImage;

import com.project.domain.ProfileImage;

public interface ImageService {
	public BufferedImage resizeUserAvatar(BufferedImage avatar);
	public ProfileImage getUserProfileImage(String username);
	public ProfileImage saveProfileImage(ProfileImage image);
}
