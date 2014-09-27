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
import com.backendtask.model.Monument;
import com.backendtask.monument.service.IMonumentService;

@Controller
public class MonumentController {
	
	@Autowired
	IMonumentService monumentService;
	
	@Autowired
	ICategoryService categoryService;
	
	private int currentCollectionId;
	private String currentCollectionName;
	
	private List<Monument> monumentsList = new ArrayList<Monument>();
	
	@RequestMapping(value = "/monumentsPage", method = RequestMethod.GET)
	public ModelAndView monumentsPage(@RequestParam("collectionId") int collectionId, @RequestParam("collectionName") String collectionName) {
		currentCollectionId = collectionId;
		currentCollectionName = collectionName;
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal() instanceof String) {
			if(auth.getPrincipal().equals("anonymousUser")) {
				model.setViewName("403");
			}
		}
		else if(auth.getPrincipal() instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			monumentsList  = monumentService.getMonuments(collectionId, userDetail.getUsername());
			model.addObject("monumentsList", monumentsList);
			model.addObject("currentCollectionName", currentCollectionName);
			model.addObject("searchedMonument", new Monument());
			model.setViewName("monumentsPage");
		}
		return model;
	}
	
	@RequestMapping(value = "/editMonument", method = RequestMethod.GET)
	public ModelAndView editMonument(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		for(Monument monument :  monumentsList) {
			if(monument.getMonumentId() == id) {
				model.addObject("title", "Edit Monument");
				model.addObject("message", "Current collection is: " + currentCollectionName + ". Use the bellow form to edit an existing monument.");
				model.addObject("action", "saveEditedMonument");
				model.addObject("monument", monument);
				model.addObject("categories", categoryService.getCategories());
				break;
			}
		}
		model.setViewName("addEditMonument");
		return model;
	}
	
	@RequestMapping(value = "/deleteMonument", method = RequestMethod.GET)
	public String deleteMonument(@RequestParam("id") int id) {
		for(Monument monument :  monumentsList) {
			if(monument.getMonumentId() == id) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				monumentService.deleteMonument(monument, userDetail.getUsername());
				break;
			}
		}
		return "successOperation";
	}
	
	@RequestMapping(value = "/addMonument", method = RequestMethod.GET)
	public ModelAndView addMonument() {
		ModelAndView model = new ModelAndView();
		Monument monument = new Monument();
		model.addObject("title", "Add Monument");
		model.addObject("message", "Current collection is: " + currentCollectionName + ". Use the bellow form to add a new monument.");
		model.addObject("action", "saveAddedMonument");
		model.addObject("monument", monument);
		model.addObject("categories", categoryService.getCategories());
		model.setViewName("addEditMonument");
		return model;
	}
	
	@RequestMapping(value = "/saveEditedMonument", method = RequestMethod.POST)
	public String saveEditedMonument(Monument monument) {
		//TODO add validator
		for(Monument c :  monumentsList) {
			if(c.getMonumentId() == monument.getMonumentId()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				monumentService.updateMonument(monument, currentCollectionId, userDetail.getUsername());
				break;
			}
		}
		
		return "successOperation";
	}
	
	@RequestMapping(value = "/saveAddedMonument", method = RequestMethod.POST)
	public String saveAddedMonument(Monument monument) {
		//TODO add validator
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		monumentService.addMonument(monument, currentCollectionId, userDetail.getUsername());
		
		return "successOperation";
	}
	
	@RequestMapping(value = "/searchMonuments", method = RequestMethod.POST)
	public ModelAndView searchMonuments(Monument searchedMonument) {
		ModelAndView model = new ModelAndView();
		model.setViewName("monumentsPage");
		model.addObject("searchedMonument", searchedMonument);
		model.addObject("currentCollectionName", currentCollectionName);
		if(searchedMonument.getMonumentName().equals("") 
				&& searchedMonument.getCategory().getCategoryName().equals("")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			monumentsList  = monumentService.getMonuments(currentCollectionId, userDetail.getUsername());
			model.addObject("monumentsList", monumentsList);
			model.addObject("searchedMonument", new Monument());
			model.setViewName("monumentsPage");
			return model;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		monumentsList  = monumentService.findMonuments(searchedMonument.getMonumentName(), searchedMonument.getCategory().getCategoryName(), userDetail.getUsername());
		model.addObject("monumentsList", monumentsList);
		return model;
	}
}