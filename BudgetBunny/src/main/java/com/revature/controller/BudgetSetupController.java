package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/budgetsetuppage")
public class BudgetSetupController {
	@RequestMapping(method=RequestMethod.GET)
	public String getBudgetSetPage()
	{
		return "budgetsetuppage ";
	}
	
}
