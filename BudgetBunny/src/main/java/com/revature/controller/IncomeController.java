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
@RequestMapping(value="/incomepage")
public class IncomeController {
	@Autowired
	RecurringChargeService rcService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIncomepage(HttpServletRequest req)
	{
		User u = (User) req.getSession().getAttribute("user");
		if(u == null)
			return "redirect:login";
		
		ArrayList<RecurringCharge> iList = rcService.getAll(u.getBudget(), true);
		req.getSession().setAttribute("incomeList", iList);
		return "incomepage";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postIncomepage(HttpServletRequest req, HttpServletResponse resp) 
	{
		String income = req.getParameter("depositData");
		String deleted = req.getParameter("deletedList");
		
		System.out.println(req.getParameter("depositData"));
		System.out.println(deleted);
		
		ArrayList<RecurringCharge> iList = new ArrayList<RecurringCharge>();
		ArrayList<Integer> dList = new ArrayList<Integer>();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = (User)req.getSession().getAttribute("user");
		try {
			JsonNode iNode = mapper.readTree(income);
			for(int i=0;i<iNode.size();i++) {
				RecurringCharge in = new RecurringCharge();
				JsonNode iJson = iNode.get(Integer.toString(i));
				in.setChargeId(iJson.get("id").asInt());
				in.setName(iJson.get("name").asText());
				in.setCost(iJson.get("cost").asDouble());
				in.setBud(user.getBudget());
//				in.setStartDate(Date.valueOf(LocalDate.parse(iJson.get("startDate").asText(), formatter)));
//				in.setLastTransactionDate(Date.valueOf(LocalDate.now()));
				iList.add(in);
			}
			
			if(deleted != null) {
				System.out.println("first");
				JsonNode dNode = mapper.readTree(deleted);
				for(int i=0;i<dNode.size();i++) {
					System.out.println("loop");
					JsonNode j = dNode.get(Integer.toString(i));
					dList.add(j.get("id").asInt());
				}			
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(dList);
		if(dList.size() > 0)
			rcService.deleteList(dList);
		Budget updatedBudget = rcService.mergeIncomeList(iList, user.getBudget());
		user.getBudget().setTotalBudget(updatedBudget.getTotalBudget());
		req.getSession().setAttribute("user", user);
		System.out.println(iList);
		
		return "incomepage";
	}
}
