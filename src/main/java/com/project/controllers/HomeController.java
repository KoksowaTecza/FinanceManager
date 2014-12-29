package com.project.controllers;

import javax.inject.Inject;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.services.UserService;

@Controller
public class HomeController {
	
	private UserService service;
	
	@Inject
	public HomeController(UserService service){
		this.service = service;
	}
	
	@RequestMapping({"/home"})
	public String showHomePage(Model model){
		return "home";
	}
	
}
