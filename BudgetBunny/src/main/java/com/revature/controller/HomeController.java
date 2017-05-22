package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Budget;
import com.revature.bean.Category;
import com.revature.bean.Transaction;
import com.revature.bean.User;
import com.revature.service.CategoryService;
import com.revature.service.TransactionService;

@Controller
@RequestMapping(value="/home")
public class HomeController
{
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private TransactionService tsService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHomepage(HttpServletRequest req)
	{		
		User u = (User)req.getSession().getAttribute("user");
		if(u == null)
			return "redirect:login";
		if(u.getBudget().getTotalBudget() == 0)
			return "budgetsetuppage";
		else {
				List<Category> list = new ArrayList<Category>();
				list = catService.getAll(u.getBudget());
				req.getSession().setAttribute("cats", list);	
		}
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postHomepage(HttpServletRequest req)
	{	
		
		String name = req.getParameter("name");
		String budgettemp = req.getParameter("budget");
		StringBuilder sb = new StringBuilder(budgettemp);
		StringBuilder sbt = sb.deleteCharAt(0);
		String sb1 = sbt.toString();
		double budget = Double.parseDouble(sb1);
		String catIDtemp = req.getParameter("id");
		String spenttemp = req.getParameter("amount");
		StringBuilder sbs = new StringBuilder(spenttemp);
		StringBuilder sbt2 = sbs.deleteCharAt(0);
		String sbsp = sbt2.toString();
		Double spent = Double.parseDouble(sbsp);
		System.out.println("PLEASE FUCKIN WORK");
		System.out.println(spent);
		int catID = Integer.parseInt(catIDtemp);
		System.out.println(catID);
		Budget bud = new Budget(catID, budget, spent,null,null);
		Category cat = new Category(catID, name, bud.getTotalBudget(), bud.getTotalSpent(), null, bud);

		
		//new transaction
		Transaction transaction = new Transaction(spent, cat);
		System.out.println(transaction.getDate().getClass().getName());
		//save transaction to DB
		tsService.save(transaction, catID);
		
		return "home";
	}
		
				
	

	
}
