package com.revature.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
			
		else {
				User u = (User) req.getSession().getAttribute("user");
				List<Category> list = new ArrayList<Category>();
				list = catService.getAll(u.getBudget());
				req.getSession().setAttribute("cats", list);	
		}
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postHomepage(HttpServletRequest req)
	{	
		//get new date object
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String catIDtemp = req.getParameter("id");
		String costtemp = req.getParameter("budget");
		int catID = Integer.parseInt(catIDtemp);
		System.out.println(catID);
		//int cost = Integer.parseInt(costtemp);

		//System.out.println(cost);
		
		
		//new transaction
		Transaction transaction = new Transaction(0, date,2);
		//save transaction to DB
		tsService.save(transaction, catID);
		
		return "home";
	}
		
				
	

	
}
