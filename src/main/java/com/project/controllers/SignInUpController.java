package com.project.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import javax.imageio.IIOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.project.domain.UserAccount;
import com.project.services.UserService;
import com.project.services.UserAuthenticationProviderService;

@Controller
@RequestMapping(value = "/account")
public class SignInUpController {

	private UserService userService;
	private UserAccount userEntity;
	private UserAuthenticationProviderService userAuthenticationProviderService;
	private EntityManager entityManager;
	
	
	@Inject
	public void setUserAuthenticationProviderService(
			UserAuthenticationProviderService userAuthenticationProviderService) {
		this.userAuthenticationProviderService = userAuthenticationProviderService;
	}

	@Inject
	public void setUserEntity(UserAccount userEntity) {
		this.userEntity = userEntity;
	}

	@Inject
	public SignInUpController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model){
		if(userAuthenticationProviderService.processUserAuthentication(username, password))
			return "redirect:/app/spittle/"+username;
		return "redirect:/app/home/";
	}

	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createSpitterProfile(Model model) {
		model.addAttribute(new UserAccount());
		return "account/create";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addSpitterFromForm(@Valid UserAccount user,
			BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			return "account/create";
		}
		
		
		try {
			if (!image.isEmpty()) {
				validateImage(image);
//				saveImage(user.getUsername()+".jpg", image);
				Session session = entityManager.unwrap(Session.class);
				Blob imageBlob = Hibernate.getLobCreator(session).createBlob(image.getInputStream(), image.getSize());
				user.setProfile_image(imageBlob);
				user.setProfile_image_content_type(image.getContentType());
				user.setProfile_image_name(image.getOriginalFilename());
			}else {
				user.setProfile_image_name("dafault");
			}
		} catch (IOException e) {
			bindingResult.reject(e.getMessage());
			e.printStackTrace();
			return "spitters/edit";
		}
		userService.createUserAccount(user);
		
		return "redirect:/app/home";
	}

	private void validateImage(MultipartFile image) throws IIOException {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new IIOException("Only JPG images accepted");
		}
	}

//	private void saveImage(String filename, MultipartFile image)
//			throws IIOException {
//		try {
//			File file = new File("C:/JavaEE/eclipse/workspace/mvc.demo/src/main/webapp/static/avatars/" + filename);
//			FileUtils.writeByteArrayToFile(file, image.getBytes());
//		} catch (IOException e) {
//			throw new IIOException("Unable to save image", e);
//		}
//	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
