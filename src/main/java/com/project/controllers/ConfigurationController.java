package com.project.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.ProfileImage;
import com.project.domain.UserAccount;
import com.project.domain.configuration.ConfigurationData;

@Controller
@RequestMapping(value = "/profile/configuration")
public class ConfigurationController {
	
	@RequestMapping(value="/finance/success", method = RequestMethod.GET)
	public String sucessMsg(Model model) {
		return "configuration/manager/success";
	}
	
	@RequestMapping(value="/finance", method = RequestMethod.GET)
	public String showFinanceTabConfiguration(Model model) {
		model.addAttribute(new ConfigurationData());
		return "configuration/manager";
	}
	
	
	@RequestMapping(value="/finance", method = RequestMethod.POST)
	public String addSpitterFromForm(@Valid ConfigurationData configurationData,
			BindingResult bindingResult, HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			return "configuration/manager";
		}
		
		return "redirect:/app/profile/configuration/finance/success";
	}
	
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String showProfileTabConfiguration(Model model) {
		model.addAttribute(new ConfigurationData());
		return "configuration/profile";
	}
	
	
}
