package com.revature.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Budget;
import com.revature.bean.Category;
import com.revature.bean.RecurringCharge;
import com.revature.bean.User;
import com.revature.service.BudgetService;

@Controller
@RequestMapping(value="/budgetsetuppage")
public class BudgetSetupController {
	@Autowired
	BudgetService budgetService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getBudgetSetPage(HttpServletRequest req)
	{
		System.out.println("Getting");
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
		return "budgetsetuppage";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postBudgetSetPage(HttpServletRequest req, HttpServletResponse resp) 
	{
		String income = req.getParameter("depositData");
		String bill = req.getParameter("withdrawData");
		String category = req.getParameter("categoryData");
		System.out.println(req.getParameter("depositData"));
		System.out.println(req.getParameter("withdrawData"));
		System.out.println(req.getParameter("categoryData"));
		
		ArrayList<Category> cList = new ArrayList<Category>();
		ArrayList<RecurringCharge> iList = new ArrayList<RecurringCharge>();
		ArrayList<RecurringCharge> bList = new ArrayList<RecurringCharge>();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = (User)req.getSession().getAttribute("user");
		try {
			JsonNode cNode = mapper.readTree(category);
			for(int i=0;i<cNode.size();i++) {
				Category c = new Category();
				JsonNode cJson = cNode.get(Integer.toString(i));
				c.setName(cJson.get("name").asText());
				c.setBudget(cJson.get("amount").asDouble());
				c.setBud(user.getBudget());
				cList.add(c);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale(Locale.US );
			
			JsonNode iNode = mapper.readTree(income);
			for(int i=0;i<iNode.size();i++) {
				RecurringCharge in = new RecurringCharge();
				JsonNode iJson = iNode.get(Integer.toString(i));
				in.setName(iJson.get("name").asText());
				in.setCost(iJson.get("cost").asDouble());
				in.setBud(user.getBudget());
//				in.setStartDate(Date.valueOf(LocalDate.parse(iJson.get("startDate").asText(), formatter)));
//				in.setLastTransactionDate(Date.valueOf(LocalDate.now()));
				iList.add(in);
			}
			
			JsonNode bNode = mapper.readTree(bill);
			for(int i=0;i<bNode.size();i++) {
				RecurringCharge b = new RecurringCharge();
				JsonNode bJson = bNode.get(Integer.toString(i));
				b.setName(bJson.get("name").asText());
				b.setCost(bJson.get("cost").asDouble());
				b.setBud(user.getBudget());
//				b.setStartDate(Date.valueOf(LocalDate.parse(bJson.get("startDate").asText(), formatter)));
//				b.setLastTransactionDate(Date.valueOf(LocalDate.now()));
				bList.add(b);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Budget updatedBudget = budgetService.saveAll(iList, bList, cList, user.getBudget());
		user.getBudget().setTotalBudget(updatedBudget.getTotalBudget());
		user.getBudget().setTotalSpent(updatedBudget.getTotalSpent());
		req.getSession().setAttribute("user", user);
		System.out.println(cList);
		System.out.println(iList);
		System.out.println(bList);
		
		return "budgetsetuppage";
	}
}
