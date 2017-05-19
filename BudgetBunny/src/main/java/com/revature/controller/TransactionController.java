package com.revature.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> 81232e13cc620518bf95f89bce8df1a67a194c88
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
<<<<<<< HEAD
			
		else {
				Transaction t = (Transaction) req.getSession().getAttribute("cat");
				List<Transaction> list = new ArrayList<Transaction>();
				list = tService.getAll(t.getCat());
				req.getSession().setAttribute("cats", list);	
		
		}
=======
>>>>>>> 81232e13cc620518bf95f89bce8df1a67a194c88
		return "transaction";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTransaction(HttpServletRequest req){
		
		return null;
	}
	
}
