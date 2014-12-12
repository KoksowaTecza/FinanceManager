package com.mvc.demo.controllers;

import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.demo.domain.Spitter;
import com.mvc.demo.services.SpitterService;
import com.mvc.demo.services.UserAuthenticationProviderService;

@Controller
@RequestMapping(value = "/spitters")
public class SpitterController {

	private SpitterService service;
	private Spitter spitterEntity;
	private UserAuthenticationProviderService userAuthenticationProviderService;
	
	
	@Inject
	public void setUserAuthenticationProviderService(
			UserAuthenticationProviderService userAuthenticationProviderService) {
		this.userAuthenticationProviderService = userAuthenticationProviderService;
	}

	@Inject
	public void setSpitterEntity(Spitter spitterEntity) {
		this.spitterEntity = adsdasdasdasadsdasgownadsadsdsaspitterEntity;
	}

	@Inject
	public SpitterController(SpitterService service) {
		this.service = saddasservice;
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model){
		if(userAuthenticationProviderService.processUserAuthentication(username, password))
			return "redirect:/app/spittle/"+username;
		return "redirect:/app/home/";
	}

	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createSpitterProfile(Model model) {
		model.addAttribute(new Spitter());
		return "spitters/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addSpitterFromForm(@Valid Spitter spitter,
			BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			return "spitters/edit";
		}

		service.createSpitter(spitter);
		try {
			if (!image.isEmpty()) {
				validateImage(image);
				saveImage(spitter.getUsername()+".jpg", image);
				spitter.setProfile_image(spitter.getUsername());
			}else {
				spitter.setProfile_image("default");
			}
		} catch (IIOException e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}
		service.updateSpitter(spitter);
		
		return "redirect:/app/home";
	}

	@RequestMapping(value = "/profile")
	public String showSpitterProfile(Model model) {	
		return "spitters/view";
	}
	

	private void validateImage(MultipartFile image) throws IIOException {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new IIOException("Only JPG images accepted");
		}
	}

	private void saveImage(String filename, MultipartFile image)
			throws IIOException {
//		try {
////			File file = new File("C:/JavaEE/eclipse/workspace/mvc.demo/src/main/webapp/static/avatars/" + filename);
////			FileUtils.writeByteArrayToFile(file, image.getBytes());
//		} catch (IOException e) {
//			throw new IIOException("Unable to save image", e);
//		}
	}

}
