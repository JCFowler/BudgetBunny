package com.revature.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	Logger log = Logger.getRootLogger();
	
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
		
		ArrayList<RecurringCharge> bList = new ArrayList<>();
		ArrayList<Integer> dList = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = (User)req.getSession().getAttribute("user");
		try {
			JsonNode bNode = mapper.readTree(bill);
			for(int i=0;i<bNode.size();i++) {
				RecurringCharge b = new RecurringCharge();
				JsonNode bJson = bNode.get(Integer.toString(i));
				b.setChargeId(bJson.get("id").asInt());
				b.setName(bJson.get("name").asText());
				b.setCost(bJson.get("cost").asDouble());
				b.setBud(user.getBudget());
				bList.add(b);
			}
			
			if(deleted != null) {
				JsonNode dNode = mapper.readTree(deleted);
				for(int i=0;i<dNode.size();i++) {
					JsonNode j = dNode.get(Integer.toString(i));
					dList.add(j.get("id").asInt());
				}			
			}
		} catch (Exception e) {
			log.error(e);
		} 
		rcService.deleteList(dList);
		Budget updatedBudget = rcService.mergeBillList(bList, user.getBudget());
		user.getBudget().setTotalBudget(updatedBudget.getTotalBudget());
		user.getBudget().setTotalSpent(updatedBudget.getTotalSpent());
		req.getSession().setAttribute("user", user);
		
		return "billpage";
	}
}
