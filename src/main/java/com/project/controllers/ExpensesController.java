package com.project.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.project.domain.ExpenseEntity;
import com.project.domain.ExpenseProjection;
import com.project.domain.JsonResponse;
import com.project.domain.RevenueEntity;
import com.project.domain.TransactionPeriodSummary;
import com.project.domain.UserSessionObject;
import com.project.services.CategoryService;
import com.project.services.ConfigurationDataService;
import com.project.services.FinanceService;

@Controller
@RequestMapping(value = "/profile/expenses")
public class ExpensesController {
	private UserSessionObject userSessionObject;
	private CategoryService categoryService;
	private FinanceService financeService;
	
	@Inject
	public ExpensesController(UserSessionObject userSessionObject, CategoryService categoryService, FinanceService financeService) {
		this.userSessionObject = userSessionObject;
		this.categoryService = categoryService;
		this.financeService = financeService;
	}
	
	
	@RequestMapping(value = "/expenses", method = RequestMethod.GET)
	public String showHome(Model model) {
		return "expenses/expenses";
	}
	
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public @ResponseBody List<TransactionPeriodSummary> getSummaryData(Model model) {
		List<TransactionPeriodSummary> data = financeService.getExpenseSummaryForCurrentPeriod(userSessionObject.getUsername());
		return data;
	}
	
	@RequestMapping(value = "/projection/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addExpenseProjection (HttpServletRequest request) {
		String category_name = request.getParameter("category_name");
		Double amount = Double.parseDouble(request.getParameter("amount").replaceAll("\\s+",""));
		if(category_name == null && amount == null)
			return null;
		Long category_id = categoryService.getCategoryExpensesIdByName(category_name);
		Long balance_id = financeService.getCurrentPeriodBalance(userSessionObject.getUsername()).getId();
		ExpenseProjection expenseProjection = new ExpenseProjection();
		expenseProjection.setAmount(amount);
		expenseProjection.setCategory_name_id(category_id);
		expenseProjection.setUser_balance_id(balance_id);
		financeService.saveExpenseProjection(expenseProjection);
		JsonResponse res = new JsonResponse();
		res.setStatus("SUCCESS");
		return res;
	}
	
	@RequestMapping(value = "/expense", method = RequestMethod.GET, params = "new")
	public String showRevenueModal(Model model) {
		Map<String,String> categories = new LinkedHashMap<String,String>();
		List<CategoryExpensesEntity> categoryList =   categoryService.getAllCategoryExpensesForUser(userSessionObject.getUsername());
		for(CategoryExpensesEntity category: categoryList){
			categories.put(Long.toString(category.getId()), category.getCategory_name());
		}
		model.addAttribute("categoryMap", categories);
		model.addAttribute(new ExpenseEntity());
		return "expenses/expenses/modal";
	}
	
	@RequestMapping(value = "/expense", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addRevenueItem(@Valid @ModelAttribute(value = "expenseEntity") ExpenseEntity expenseEntity,
			BindingResult result, HttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			expenseEntity.setUsername(userSessionObject.getUsername());
			ExpenseEntity newExpense = financeService.addNewExpense(expenseEntity);
			//res.setResult(categoryService.careateNewRevenueCategory(categoryRevenueEntity));
		}else {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		return res;
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
