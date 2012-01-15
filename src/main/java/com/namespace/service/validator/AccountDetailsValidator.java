package com.namespace.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.namespace.service.dto.AccountDetailsForm;

@Component
public class AccountDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDetailsForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstname_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email_required");
	}
	
}
