package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Category;
import com.revature.bean.Transaction;
import com.revature.bean.User;
import com.revature.service.CategoryService;
import com.revature.service.TransactionService;

@Controller
@RequestMapping(value="/transaction")
public class TransactionController {
	private String transaction = "transaction";
	@Autowired
	private	TransactionService tService;
	
	@Autowired 
	CategoryService cService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTransactionpage(HttpServletRequest req)
	{
		
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
			
		else {
				User u = (User) req.getSession().getAttribute("user");
				List<Transaction> list = tService.getAll(u.getBudget().getBudgetId());
				req.getSession().setAttribute(transaction, list);	
				
				
				List<Category> cList = cService.getAll(u.getBudget());
				req.getSession().setAttribute("cats", cList);		
		} 

		return transaction;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTransaction(HttpServletRequest req){
		
		int deleted = Integer.parseInt(req.getParameter("transactionId"));
		User u = (User)req.getSession().getAttribute("user");
		double cost = tService.deleteById(deleted, u.getBudget());
		u.getBudget().setTotalBudget(u.getBudget().getTotalBudget() + cost);
		u.getBudget().setTotalSpent(u.getBudget().getTotalSpent() - cost);
		req.getSession().setAttribute("user", u);
		
		return transaction;
	}
	
}
