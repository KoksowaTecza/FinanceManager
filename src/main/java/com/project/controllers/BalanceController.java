package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.domain.UserAccount;

@Controller
@RequestMapping(value = "/balance")
public class BalanceController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		return "balance";
	}
}
