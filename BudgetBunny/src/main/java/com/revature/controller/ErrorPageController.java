package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController {
	@RequestMapping(value="/errorPage", method=RequestMethod.GET)
	public String getErrorPage()
	{
		return "errorPage ";
	}
	
	
	
	
	
}
