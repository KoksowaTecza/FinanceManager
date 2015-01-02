package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/profile/reports")
public class ReportsController {
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		return "reports";
	}
}
