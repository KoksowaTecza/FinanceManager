package com.project.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.domain.CategoryRevenueEntity;
import com.project.domain.ConfigurationData;
import com.project.domain.JsonResponse;
import com.project.domain.UserSessionObject;
import com.project.services.ConfigurationDataService;
import com.project.services.FinanceService;

@Controller
@RequestMapping(value = "/profile/revenues")
public class RevenuesController {
	private ConfigurationDataService configurationDataService;
	private UserSessionObject userSessionObject;
	private FinanceService financeService;

	@Inject
	public RevenuesController(
			ConfigurationDataService configurationDataService,
			UserSessionObject userSessionObject, FinanceService financeService) {
		this.configurationDataService = configurationDataService;
		this.userSessionObject = userSessionObject;
		this.financeService = financeService;
	}

	@RequestMapping(value = "/revenues", method = RequestMethod.GET)
	public String showRevenuesTab(Model model) {
		return "revenues/revenues";
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String showCategoriesTab(Model model) {
		List<CategoryRevenueEntity> list = financeService.getAllCategoryRevenuesForUser(userSessionObject.getUsername());
		model.addAttribute("categoryList", list);
		return "revenues/categories";
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, params = "new")
	public String showModalAddCategory(Model model) {
		model.addAttribute(new CategoryRevenueEntity());
		return "revenues/categories/modal";
	}
	
	@RequestMapping(value = "/categories/{catname}", method = RequestMethod.GET, params = "edit")
	public String showModalEditCategory(@PathVariable String catname, Model model) {
		model.addAttribute("edit", "edit");
		model.addAttribute(financeService.getcategoryRevenueEntity(catname));
		return "revenues/categories/modal";
	}
	
	

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addSpitterFromForm(@Valid @ModelAttribute(value = "categoryRevenueEntity") CategoryRevenueEntity categoryRevenueEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryRevenueEntity.setUsername(userSessionObject.getUsername());
			res.setResult(financeService.careateNewRevenueCategory(categoryRevenueEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST, params = "edit")
	public @ResponseBody JsonResponse editCategory(@Valid @ModelAttribute(value = "categoryRevenueEntity") CategoryRevenueEntity categoryRevenueEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryRevenueEntity.setUsername(userSessionObject.getUsername());
			res.setResult(financeService.updateCategoryRevenueEntity(categoryRevenueEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}

}
