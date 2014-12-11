package com.mvc.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/spittle")
public class SpittleController {
	
	
	@RequestMapping(value="/addSpitlle", method = RequestMethod.POST)
	public String addSpittle(@RequestParam String message, Model model){
		return "userSpittles/view";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String showUserSpittles(@PathVariable String username, Model model){
		return "userSpittles/view";
	}

}
