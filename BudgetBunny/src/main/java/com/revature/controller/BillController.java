package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import com.revature.bean.RecurringCharge;
import com.revature.bean.User;
import com.revature.service.RecurringChargeService;

@Controller
@RequestMapping(value="/billpage")
public class BillController {
	@Autowired
	RecurringChargeService rcService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getBillPage(HttpServletRequest req)
	{
		User u = (User) req.getSession().getAttribute("user");
		if(u == null)
			return "redirect:login";
		
		ArrayList<RecurringCharge> bList = rcService.getAll(u.getBudget(), false);
		req.getSession().setAttribute("billList", bList);
		return "billpage";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postBillPage(HttpServletRequest req, HttpServletResponse resp) 
	{
		String bill = req.getParameter("withdrawData");
		String deleted = req.getParameter("deletedList");
		
		System.out.println(req.getParameter("withdrawData"));
		System.out.println(deleted);
		
		ArrayList<RecurringCharge> bList = new ArrayList<RecurringCharge>();
		ArrayList<Integer> dList = new ArrayList<Integer>();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = (User)req.getSession().getAttribute("user");
		try {
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
			
			JsonNode dNode = mapper.readTree(deleted);
			for(int i=0;i<dNode.size();i++) {
				JsonNode j = dNode.get(Integer.toString(i));
				dList.add(j.get("id").asInt());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rcService.deleteList(dList);
		Budget updatedBudget = rcService.mergeBillList(bList, user.getBudget());
		user.getBudget().setTotalBudget(updatedBudget.getTotalBudget());
		user.getBudget().setTotalSpent(updatedBudget.getTotalSpent());
		req.getSession().setAttribute("user", user);
		System.out.println(bList);
		
		return "budgetsetuppage";
	}
}
