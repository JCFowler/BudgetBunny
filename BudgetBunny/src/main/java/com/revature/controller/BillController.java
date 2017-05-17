package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BillController {
	@RequestMapping(value="/BillPage", method=RequestMethod.GET)
	public String getBillPage()
	{
		return "BillPage ";
	}
	
}
