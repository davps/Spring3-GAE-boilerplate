package com.namespace.service.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.namespace.service.dto.UserAdministrationForm;

public class UserAdministrationCommonsValidations {

	public UserAdministrationCommonsValidations() {
		super();
	}

	protected void commonValidations(Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email_required");
	}

	protected void passwordValidation(Errors errors, UserAdministrationForm user) {
		if(! user.getPassword().equals( user.getRetypePassword() ) ){
			errors.rejectValue("password", "password_matches");
			errors.rejectValue("retypePassword", "password_matches");
		}
		ValidationUtils.rejectIfEmpty(errors, "password", "password_empty");
		ValidationUtils.rejectIfEmpty(errors, "retypePassword", "password_empty");
	}
	
}