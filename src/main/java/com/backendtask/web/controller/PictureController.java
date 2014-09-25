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
import com.backendtask.model.Picture;
import com.backendtask.picture.service.IPictureService;

@Controller
public class PictureController {
	
	@Autowired
	IPictureService pictureService;
	
	@Autowired
	ICategoryService categoryService;
	
	private int currentMonumentId;
	private String currentMonumentName;
	
	private List<Picture> picturesList = new ArrayList<Picture>();
	
	@RequestMapping(value = "/picturesPage", method = RequestMethod.GET)
	public ModelAndView picturesPage(@RequestParam("monumentId") int monumentId, @RequestParam("monumentName") String monumentName) {
		currentMonumentId = monumentId;
		currentMonumentName = monumentName;
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal() instanceof String) {
			if(auth.getPrincipal().equals("anonymousUser")) {
				model.setViewName("403");
			}
		}
		else if(auth.getPrincipal() instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			picturesList  = pictureService.getPictures(monumentId, userDetail.getUsername());
			model.addObject("picturesList", picturesList);
			model.addObject("currentMonumentName", currentMonumentName);
			model.setViewName("picturesPage");
		}
		return model;
	}
	
	@RequestMapping(value = "/editPicture", method = RequestMethod.GET)
	public ModelAndView editPicture(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		for(Picture picture :  picturesList) {
			if(picture.getPictureId() == id) {
				model.addObject("title", "Edit Picture");
				model.addObject("message", "Current monument is: " + currentMonumentName + ". Use the bellow form to edit an existing picture.");
				model.addObject("action", "saveEditedPicture");
				model.addObject("picture", picture);
				model.addObject("categories", categoryService.getCategories());
				break;
			}
		}
		model.setViewName("addEditPicture");
		return model;
	}
	
	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public String deletePicture(@RequestParam("id") int id) {
		for(Picture picture :  picturesList) {
			if(picture.getPictureId() == id) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				pictureService.deletePicture(picture, userDetail.getUsername());
				break;
			}
		}
		return "successOperation";
	}
	
	@RequestMapping(value = "/addPicture", method = RequestMethod.GET)
	public ModelAndView addPicture() {
		ModelAndView model = new ModelAndView();
		Picture picture = new Picture();
		model.addObject("title", "Add Picture");
		model.addObject("message", "Current monument is: " + currentMonumentName + ". Use the bellow form to add a new picture.");
		model.addObject("action", "saveAddedPicture");
		model.addObject("picture", picture);
		model.addObject("categories", categoryService.getCategories());
		model.setViewName("addEditPicture");
		return model;
	}
	
	@RequestMapping(value = "/saveEditedPicture", method = RequestMethod.POST)
	public String saveEditedPicture(Picture picture) {
		//TODO add validator
		for(Picture c :  picturesList) {
			if(c.getPictureId() == picture.getPictureId()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				pictureService.updatePicture(picture, currentMonumentId, userDetail.getUsername());
				break;
			}
		}
		
		return "successOperation";
	}
	
	@RequestMapping(value = "/saveAddedPicture", method = RequestMethod.POST)
	public String saveAddedPicture(Picture picture) {
		//TODO add validator
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		pictureService.addPicture(picture, currentMonumentId, userDetail.getUsername());
		
		return "successOperation";
	}
}