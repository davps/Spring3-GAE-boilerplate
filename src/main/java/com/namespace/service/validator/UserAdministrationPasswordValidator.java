package com.namespace.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.namespace.service.dto.UserAdministrationForm;

@Component
public class UserAdministrationPasswordValidator extends UserAdministrationCommonsValidations implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAdministrationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAdministrationForm user = (UserAdministrationForm) target;
		
		passwordValidation(errors, user);
	}

}
