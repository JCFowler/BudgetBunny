package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/reportspage")
public class ReportsController {

	@RequestMapping(method = RequestMethod.GET)
	public String getReportsPage(HttpServletRequest req) {
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
		return "reportspage";
	}

}
