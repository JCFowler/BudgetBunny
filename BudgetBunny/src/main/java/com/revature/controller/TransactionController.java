package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Transaction;
import com.revature.bean.User;
import com.revature.service.TransactionService;

@Controller
@RequestMapping(value="/transaction")
public class TransactionController {
	@Autowired
	private	TransactionService tService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTransactionpage(HttpServletRequest req)
	{
		
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
			
		else {
				User u = (User) req.getSession().getAttribute("user");
				List<Transaction> list = new ArrayList<Transaction>();
				list = tService.getAll(u.getBudget().getBudgetId());
				req.getSession().setAttribute("transaction", list);	
		 
		} 
		return "transaction";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTransaction(HttpServletRequest req){
		
		String deleted = req.getParameter("");
		ArrayList<Integer> dlist = new ArrayList<Integer>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode tNode = mapper.readTree(deleted);
			for (int i = 0; i < tNode.size(); i++) {
				JsonNode tJson = tNode.get(Integer.toString(i));
				dlist.add(tJson.get("id").asInt());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		tService.delete(dlist);
		
		return null;
	}
	
}
