package com.backendtask.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backendtask.collection.service.ICollectionService;
import com.backendtask.model.Collection;

@Controller
public class CollectionController {
	
	@Autowired
	ICollectionService collectionService;
	
	private List<Collection> collectionsList = new ArrayList<Collection>();
	
	@RequestMapping(value = "/collectionsPage", method = RequestMethod.GET)
	public ModelAndView collectionsPage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal() instanceof String) {
			if(auth.getPrincipal().equals("anonymousUser")) {
				model.setViewName("403");
			}
		}
		else if(auth.getPrincipal() instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			collectionsList  = collectionService.getCollections(userDetail.getUsername());
			model.addObject("collectionsList", collectionsList);
			model.setViewName("collectionsPage");
		}
		return model;
	}
	
	@RequestMapping(value = "/editCollection", method = RequestMethod.GET)
	public ModelAndView editCollection(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		for(Collection collection :  collectionsList) {
			if(collection.getCollectionId() == id) {
				model.addObject("title", "Edit Collection");
				model.addObject("message", "Use the bellow form to edit an existing collection.");
				model.addObject("action", "saveEditedCollection");
				model.addObject("collection", collection);
				break;
			}
		}
		model.setViewName("addEditCollection");
		return model;
	}
	
	@RequestMapping(value = "/deleteCollection", method = RequestMethod.GET)
	public String deleteCollection(@RequestParam("id") int id) {
		for(Collection collection :  collectionsList) {
			if(collection.getCollectionId() == id) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				collectionService.deleteCollection(collection, userDetail.getUsername());
				break;
			}
		}
		return "successOperation";
	}
	
	@RequestMapping(value = "/addCollection", method = RequestMethod.GET)
	public ModelAndView addCollection() {
		ModelAndView model = new ModelAndView();
		Collection collection = new Collection();
		model.addObject("title", "Add Collection");
		model.addObject("message", "Use the bellow form to add a new collection.");
		model.addObject("action", "saveAddedCollection");
		model.addObject("collection", collection);
		model.setViewName("addEditCollection");
		return model;
	}
	
	@RequestMapping(value = "/saveEditedCollection", method = RequestMethod.POST)
	public String saveEditedCollection(Collection collection) {
		//TODO add validator
		for(Collection c :  collectionsList) {
			if(c.getCollectionId() == collection.getCollectionId()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				collectionService.updateCollection(collection, userDetail.getUsername());
				break;
			}
		}
		
		return "successOperation";
	}
	
	@RequestMapping(value = "/saveAddedCollection", method = RequestMethod.POST)
	public String saveAddedCollection(Collection collection) {
		//TODO add validator
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		collectionService.addCollection(collection, userDetail.getUsername());
		
		return "successOperation";
	}
}