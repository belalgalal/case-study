package com.backendtask.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.backendtask.model.User;

@Component("registrationValidator")
public class RegitrationValidator {

	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.newUser.userName", "User Name must not be Empty.");
		String userName = user.getUserName();
		if ((userName.length()) > 45) {
			errors.rejectValue("userName",
					"lengthOfUser.newUser.userName",
					"User Name must not more than 45 characters.");
		}
		if (!(user.getPassword()).equals(user.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.newUser.password",
					"Password and Confirm Password Not match.");
		}
	}
}
