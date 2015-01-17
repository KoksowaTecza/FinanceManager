package com.project.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.domain.CategoryExpensesEntity;
import com.project.domain.CategoryRevenueEntity;
import com.project.domain.JsonResponse;
import com.project.domain.UserSessionObject;
import com.project.services.CategoryService;
import com.project.services.ConfigurationDataService;
import com.project.services.FinanceService;

@Controller
@RequestMapping(value = "/profile/expenses")
public class ExpensesController {
	private UserSessionObject userSessionObject;
	private CategoryService categoryService;
	
	@Inject
	public ExpensesController(UserSessionObject userSessionObject, CategoryService categoryService) {
		this.userSessionObject = userSessionObject;
		this.categoryService = categoryService;
	}
	
	
	@RequestMapping(value = "/expenses", method = RequestMethod.GET)
	public String showHome(Model model) {
		return "expenses/expenses";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String showCategoriesTab(Model model, HttpServletRequest request) {
		List<CategoryExpensesEntity> list = categoryService.getAllCategoryExpensesForUser(userSessionObject.getUsername());
		model.addAttribute("categoryList", list);
		if(request.getParameter("success")!=null){
			String req = request.getParameter("success");
			model.addAttribute("success", req);
		}
		return "expenses/categories";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET, params = "new")
	public String showModalAddCategory(Model model) {
		model.addAttribute(new CategoryExpensesEntity());
		return "expenses/categories/modal";
	}
	
	@RequestMapping(value = "/categories/{catname}", method = RequestMethod.GET, params = "edit")
	public String showModalEditCategory(@PathVariable String catname, Model model) {
		model.addAttribute("edit", "edit");
		model.addAttribute(categoryService.getCategoryExpensesEntity(catname));
		return "expenses/categories/modal";
	}
	
	@RequestMapping(value = "/categories/{catname}", method = RequestMethod.GET, params = "delete")
	public @ResponseBody JsonResponse deleteSelectedCategory(@PathVariable String catname, Model model) {
		boolean response = categoryService.daleteExpensesCategoryByName(catname);
		JsonResponse res = new JsonResponse();
		if(response == true){
			res.setStatus("SUCCESS");
		}else {
			res.setStatus("ERROR");
		}
		res.setStatus("SUCCESS");
		res.setResult(catname);
		return res;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST, params = "edit")
	public @ResponseBody JsonResponse editCategory(@Valid @ModelAttribute(value = "categoryExpensesEntity") CategoryExpensesEntity categoryExpensesEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryExpensesEntity.setUsername(userSessionObject.getUsername());
			res.setResult(categoryService.updateCategoryExpensesEntity(categoryExpensesEntity));
			res.setAddings("edit");
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
	
	

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addSpitterFromForm(@Valid @ModelAttribute(value = "categoryExpensesEntity") CategoryExpensesEntity categoryExpensesEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			categoryExpensesEntity.setUsername(userSessionObject.getUsername());
			res.setResult(categoryService.careateNewExpensesCategory(categoryExpensesEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
	}
}
