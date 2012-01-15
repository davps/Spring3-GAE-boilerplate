package com.namespace.web;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonsController {

//	@RequestMapping(value="/uncaughtException", method=RequestMethod.GET)
	public String uncaughtExceptionHandler(){
		return "commons/uncaughtException";
	}
	
	
//	@RequestMapping(value="/resourceNotFound", method=RequestMethod.GET)
	public String resourceNotFound(){
		return "commons/resourceNotFound";
	}
	
}
