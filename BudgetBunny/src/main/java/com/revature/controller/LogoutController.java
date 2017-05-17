package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	@RequestMapping(value="/logoutpage", method=RequestMethod.GET)
	public String getLogoutPage()
	{
		return "logoutpage ";
	}
	
}
