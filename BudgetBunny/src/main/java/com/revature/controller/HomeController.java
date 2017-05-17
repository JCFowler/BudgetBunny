package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/Home")
public class HomeController
{
	@RequestMapping(method=RequestMethod.GET)
	public String getHomepage()
	{
		return "Home";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String toHomePage(HttpServletRequest req, HttpSession session)
	{
		return "Home";
		
	}

	
}
