package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Transaction;
import com.revature.service.TransactionService;

@Controller
@RequestMapping(value="/reports")
public class reportController {
				
		@RequestMapping(method=RequestMethod.GET)
		public String getReportPage(HttpServletRequest req)
		{
			return "reports";
		}
}
