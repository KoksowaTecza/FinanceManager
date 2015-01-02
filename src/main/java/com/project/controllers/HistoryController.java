package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/history")
public class HistoryController {
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		return "history";
	}
}
