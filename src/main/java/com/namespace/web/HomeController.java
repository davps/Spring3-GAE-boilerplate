package com.namespace.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.namespace.service.AbstractCurrentUserManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends AbstractCurrentUserManager {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		return "home";
	}

}

