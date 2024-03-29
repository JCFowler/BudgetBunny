package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.revature.bean.GraphTransactionBean;
import com.revature.bean.Transaction;
import com.revature.bean.User;
import com.revature.service.TransactionService;

@Controller
@RequestMapping(value = "/reports")
public class ReportsController {

	@Autowired
	private	TransactionService tService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getReportPage(HttpServletRequest req) {
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
		
		return "reports";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postReportPage(HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		
		if(u == null)
			return "redirect:login";
		
		Gson gson = new Gson();

		List<Transaction> list = tService.getAll(u.getBudget().getBudgetId());
		req.getSession().setAttribute("transaction", list);	
			
		GraphTransactionBean b = new GraphTransactionBean();
		for(Transaction tran : list)
			b.addTransaction(tran);
		
		String jsonInString = gson.toJson(b);
			
		req.setAttribute("data", jsonInString);
				 
		return "chartRenderer";
	}

}
