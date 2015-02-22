package com.project.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.project.domain.RevenueEntity;
import com.project.domain.RevenueEntity;
import com.project.domain.UserSessionObject;
import com.project.services.CategoryService;
import com.project.services.ConfigurationDataService;
import com.project.services.FinanceService;

@Controller
@RequestMapping(value = "/profile/revenues")
public class RevenuesController {
	Logger logger = LoggerFactory.getLogger(RevenuesController.class);
	
	private ConfigurationDataService configurationDataService;
	private UserSessionObject userSessionObject;
	private FinanceService financeService;
	private CategoryService categoryService;

	@Inject
	public RevenuesController(
			ConfigurationDataService configurationDataService,
			UserSessionObject userSessionObject, FinanceService financeService, CategoryService categoryService) {
		this.configurationDataService = configurationDataService;
		this.userSessionObject = userSessionObject;
		this.financeService = financeService;
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "/revenues", method = RequestMethod.GET)
	public String showRevenuesTab(Model model) {
		model.addAttribute("balance", financeService.getBalance(userSessionObject.getUsername()));
		return "revenues/revenues";
	}
	
	@RequestMapping(value = "/revenue", method = RequestMethod.GET, params = "new")
	public String showRevenueModal(Model model) {
		Map<String,String> categories = new LinkedHashMap<String,String>();
		List<CategoryRevenueEntity> categoryList =   categoryService.getAllCategoryRevenuesForUser(userSessionObject.getUsername());
		for(CategoryRevenueEntity category: categoryList){
			categories.put(Integer.toString(category.getId()), category.getCategory_name());
		}
		model.addAttribute("categoryMap", categories);
		model.addAttribute(new RevenueEntity());
		return "revenues/revenues/modal";
	}
	
	@RequestMapping(value = "/revenue", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addRevenueItem(@Valid @ModelAttribute(value = "revenueEntity") RevenueEntity revenueEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			revenueEntity.setUsername(userSessionObject.getUsername());
			RevenueEntity newRevenue = financeService.addNewRevenue(revenueEntity);
			logger.warn("Created Revenue Entity");
			
			//res.setResult(categoryService.careateNewRevenueCategory(categoryRevenueEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String showCategoriesTab(Model model, HttpServletRequest request) {
		List<CategoryRevenueEntity> list = categoryService.getAllCategoryRevenuesForUser(userSessionObject.getUsername());
		model.addAttribute("categoryList", list);
		if(request.getParameter("success")!=null){
			String req = request.getParameter("success");
			model.addAttribute("success", req);
		}
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
		model.addAttribute(categoryService.getCategoryRevenueEntity(catname));
		return "revenues/categories/modal";
	}
	
	@RequestMapping(value = "/categories/{catname}", method = RequestMethod.GET, params = "delete")
	public @ResponseBody JsonResponse deleteSelectedCategory(@PathVariable String catname, Model model) {
		categoryService.daleteRevenueCategoryByName(catname);
		JsonResponse res = new JsonResponse();
		res.setStatus("SUCCESS");
		res.setResult(catname);
		return res;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST, params = "edit")
	public @ResponseBody JsonResponse editCategory(@Valid @ModelAttribute(value = "categoryRevenueEntity") CategoryRevenueEntity categoryRevenueEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryRevenueEntity.setUsername(userSessionObject.getUsername());
			res.setResult(categoryService.updateCategoryRevenueEntity(categoryRevenueEntity));
			res.setAddings("edit");
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addSpitterFromForm(@Valid @ModelAttribute(value = "categoryRevenueEntity") CategoryRevenueEntity categoryRevenueEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryRevenueEntity.setUsername(userSessionObject.getUsername());
			res.setResult(categoryService.careateNewRevenueCategory(categoryRevenueEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	

}
