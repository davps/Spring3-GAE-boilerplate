package com.namespace.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

public class BinderUtil {

	public static WebDataBinder setUpBinder(Object form, Validator validator, HttpServletRequest request) {
		WebDataBinder binder = new WebDataBinder(form);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
		
		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
		return binder;
	}

}
