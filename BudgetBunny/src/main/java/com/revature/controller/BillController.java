package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/BillPage")
public class BillController {
	@RequestMapping(method=RequestMethod.GET)
	public String getBillPage()
	{
		return "BillPage ";
	}
	
}
