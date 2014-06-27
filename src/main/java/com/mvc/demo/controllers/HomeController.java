package com.mvc.demo.controllers;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.services.SpitterService;

@Controller
public class HomeController {
	
	private SpitterService service;
	
	@Inject
	public HomeController(SpitterService service){
		this.service = service;
	}
	
	@RequestMapping({"/home"})
	public String showHomePage(Map<String, Object> model){
		return "home";
	}
	
}
