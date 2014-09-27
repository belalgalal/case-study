package com.backendtask.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backendtask.category.service.ICategoryService;
import com.backendtask.model.Category;

@Controller
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	private List<Category> categoriesList = new ArrayList<Category>();
	
	@RequestMapping(value = "/categoriesPage", method = RequestMethod.GET)
	public ModelAndView categoriesPage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal() instanceof String) {
			if(auth.getPrincipal().equals("anonymousUser")) {
				model.setViewName("403");
			}
		}
		else if(auth.getPrincipal() instanceof UserDetails) {
			categoriesList  = categoryService.getCategories();
			model.addObject("categoriesList", categoriesList);
			model.setViewName("categoriesPage");
		}
		return model;
	}
	
	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public ModelAndView editCategory(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		for(Category category :  categoriesList) {
			if(category.getCategoryId() == id) {
				model.addObject("title", "Edit Category");
				model.addObject("message", "Use the bellow form to edit an existing category.");
				model.addObject("action", "saveEditedCategory");
				model.addObject("category", category);
				break;
			}
		}
		model.setViewName("addEditCategory");
		return model;
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(@RequestParam("id") int id) {
		for(Category category :  categoriesList) {
			if(category.getCategoryId() == id) {
				categoryService.deleteCategory(category);
				break;
			}
		}
		return "successOperation";
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory() {
		ModelAndView model = new ModelAndView();
		Category category = new Category();
		model.addObject("title", "Add Category");
		model.addObject("message", "Use the bellow form to add a new category.");
		model.addObject("action", "saveAddedCategory");
		model.addObject("category", category);
		model.setViewName("addEditCategory");
		return model;
	}
	
	@RequestMapping(value = "/saveEditedCategory", method = RequestMethod.POST)
	public String saveEditedCategory(Category category) {
		//TODO add validator
		for(Category c :  categoriesList) {
			if(c.getCategoryId() == category.getCategoryId()) {
				categoryService.updateCategory(category);
				break;
			}
		}
		
		return "successOperation";
	}
	
	@RequestMapping(value = "/saveAddedCategory", method = RequestMethod.POST)
	public String saveAddedCategory(Category category) {
		//TODO add validator
		categoryService.addCategory(category);
		return "successOperation";
	}
}