package com.project.services.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.project.services.ImageService;

public class ImageServiceImpl implements ImageService {
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

}
