package com.project.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.inject.Inject;
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

import com.project.domain.BalanceEntity;
import com.project.domain.ConfigurationData;
import com.project.domain.ProfileImage;
import com.project.domain.UserAccount;
import com.project.domain.UserSessionObject;
import com.project.services.ConfigurationDataService;

@Controller
@RequestMapping(value = "/profile/configuration")
public class ConfigurationController {
	private ConfigurationDataService configurationDataService;
	private UserSessionObject userSessionObject;
	
	@Inject
	public ConfigurationController(ConfigurationDataService configurationDataService, UserSessionObject userSessionObject){
		this.configurationDataService = configurationDataService;
		this.userSessionObject = userSessionObject;
	}
	
	@RequestMapping(value="/finance/success", method = RequestMethod.GET)
	public String sucessMsg(Model model) {
		return "configuration/manager/success";
	}
	
	@RequestMapping(value="/finance", method = RequestMethod.GET)
	public String showFinanceTabConfiguration(Model model) {
		ConfigurationData configurationData = configurationDataService.getConfigurationDataByUsername(userSessionObject.getUsername());
		if(configurationData!=null){
			model.addAttribute(configurationData);
			model.addAttribute("edit", "edit");
			return "configuration/manager";
		}
		model.addAttribute(new ConfigurationData());
		return "configuration/manager";
	}
	
	
	@RequestMapping(value="/finance", method = RequestMethod.POST)
	public String addSpitterFromForm(@Valid ConfigurationData configurationData,
			BindingResult bindingResult, HttpServletRequest request) {
		
		String edit = request.getParameter("edit");
		if (bindingResult.hasErrors()) {
			if(edit != null && edit.equals("edit")){
				return "configuration/manager?edit=edit";
			}
			return "configuration/manager";
		}
		configurationData.setUsername(userSessionObject.getUsername());
		BalanceEntity balanceEntity = new BalanceEntity();
		balanceEntity.setUsername(userSessionObject.getUsername());
		balanceEntity.setBalance(configurationData.getStartamount());
		balanceEntity.setPeriod_start(new Date());
		
		if(edit != null && edit.equals("edit")){
			configurationDataService.updateConfigurationData(configurationData, balanceEntity);
			return "redirect:/app/profile/configuration/finance/success";
		}
		configurationDataService.saveConfigurationData(configurationData, balanceEntity);
		
		return "redirect:/app/profile/configuration/finance/success";
	}
	
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String showProfileTabConfiguration(Model model) {
		model.addAttribute(new ConfigurationData());
		return "configuration/profile";
	}
	
	
}
