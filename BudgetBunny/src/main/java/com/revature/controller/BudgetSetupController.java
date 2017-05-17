package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BudgetSetupController {
	@RequestMapping(value="/BudgetPage", method=RequestMethod.GET)
	public String getBudgetSetPage()
	{
		return "BudgetPage ";
	}
	
}
