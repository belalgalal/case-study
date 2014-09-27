package com.backendtask.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backendtask.helper.HashCodeUtil;
import com.backendtask.model.User;
import com.backendtask.model.UserRole;
import com.backendtask.user.service.IUserService;
import com.backendtask.web.validator.RegitrationValidator;

/**
 * 
 * @author Belal
 *
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	IUserService userService;
	
	@Autowired
	RegitrationValidator registrationValidator;
	
	public void setRegistrationValidator(
			RegitrationValidator registrationValidator) {
		this.registrationValidator = registrationValidator;
	}
	
	/**
	 * Display the form on the get request
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public String showRegistration(Map model) {
		User user = new User();
		model.put("user", user);
		return "register";
	}

	/**
	 * Process the form on the post request
	 * 
	 * @param registration
	 * @param result
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processRegistration(@Valid User user, BindingResult result) {
		// set custom Validation by user
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
        registrationValidator.validate(user, result);
        if (result.hasErrors()) {
        	model.setViewName("register");
        	return model;
        }
        
		user.setPassword(HashCodeUtil.getHashPassword(user.getPassword()));
		user.setConfirmPassword(HashCodeUtil.getHashPassword(user.getConfirmPassword()));
		user.setEnabled(true);
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRole.setUser(user);
		user.addUserRole(userRole);
		userService.addNewUser(user, userRole);
		model.setViewName("success");
		return model;
	}

}
