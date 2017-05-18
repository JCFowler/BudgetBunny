package com.revature.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Category;
import com.revature.bean.RecurringCharge;

@Controller
@RequestMapping(value="/budgetsetuppage")
public class BudgetSetupController {
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
		try {
			JsonNode cNode = mapper.readTree(category);
			for(int i=0;i<cNode.size();i++) {
				Category c = new Category();
				JsonNode cJson = cNode.get(Integer.toString(i));
				c.setName(cJson.get("name").toString());
				c.setBudget(cJson.get("amount").asDouble());
				cList.add(c);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale(Locale.US );
			
			JsonNode iNode = mapper.readTree(income);
			for(int i=0;i<iNode.size();i++) {
				RecurringCharge in = new RecurringCharge();
				JsonNode iJson = iNode.get(Integer.toString(i));
				System.out.println("here");
				System.out.println(iJson.get("startDate").toString());
				System.out.println(LocalDate.parse(iJson.get("startDate").toString(), formatter));
				in.setName(iJson.get("name").toString());
				in.setCost(iJson.get("cost").asDouble());
				in.setStartDate(LocalDate.parse(iJson.get("startDate").toString(), formatter));
				in.setLastTransactionDate(LocalDate.parse(iJson.get("startDate").toString(), formatter));
				in.setTimeInterval(iJson.get("period").asInt());
				iList.add(in);
			}
			
			JsonNode bNode = mapper.readTree(bill);
			for(int i=0;i<bNode.size();i++) {
				RecurringCharge b = new RecurringCharge();
				JsonNode bJson = bNode.get(Integer.toString(i));
				b.setName(bJson.get("name").toString());
				b.setCost(bJson.get("cost").asDouble());
				b.setStartDate(LocalDate.parse(bJson.get("startDate").toString(), formatter));
				b.setLastTransactionDate(LocalDate.parse(bJson.get("startDate").toString(), formatter));
				b.setTimeInterval(bJson.get("period").asInt());
				bList.add(b);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(cList);
		System.out.println(iList);
		System.out.println(bList);
//		System.out.println(cList);
//		RecurringCharge r = new Gson().fromJson(withdraw, RecurringCharge.class);
//		RecurringCharge rc = new Gson().fromJson(deposit, RecurringCharge.class);
//		for(Category c1 : yourList) {
//			System.out.println(c1);
//		}
//		System.out.println(c);
//		System.out.println(r);
//		System.out.println(rc);
		
		
		
		System.out.println("Posting!");
		return "budgetsetuppage";
	}
}
