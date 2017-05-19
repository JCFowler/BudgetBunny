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
@RequestMapping(value="/transaction")
public class TransactionController {
	
	private	TransactionService tService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTransactionpage(HttpServletRequest req)
	{
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
			
		else {
				Transaction t = (Transaction) req.getSession().getAttribute("cat");
				List<Transaction> list = new ArrayList<Transaction>();
				list = tService.getAll(t.getCat());
				req.getSession().setAttribute("cats", list);	
		
		}
		return "transaction";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTransaction(HttpServletRequest req){
		
		return null;
	}
	
}
