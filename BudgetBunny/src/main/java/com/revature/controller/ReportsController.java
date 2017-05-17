package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportsController {

	@RequestMapping(value = "/ReportsPage", method = RequestMethod.GET)
	public String getReportsPage() {
		return "ReportsPage ";
	}

}
