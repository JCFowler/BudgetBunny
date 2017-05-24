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
import com.revature.bean.Category;
import com.revature.bean.User;
import com.revature.service.CategoryService;

@Controller
@RequestMapping(value="/budgetpage")
public class BudgetController {
	@Autowired
	CategoryService categoryService;
	Logger log = Logger.getRootLogger();
	
	@RequestMapping(method=RequestMethod.GET)
	public String getBudgetPage(HttpServletRequest req)
	{
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
		User u = (User)req.getSession().getAttribute("user");
		ArrayList<Category> cList = categoryService.getAll(u.getBudget());
		
		req.getSession().setAttribute("catList", cList);
		
		return "budgetpage";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postBudgetPage(HttpServletRequest req, HttpServletResponse resp) 
	{
		String category = req.getParameter("categoryData");
		String deleted = req.getParameter("deletedList");
		
		ArrayList<Category> cList = new ArrayList<>();
		ArrayList<Integer> dList = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = (User)req.getSession().getAttribute("user");
		try {
			JsonNode cNode = mapper.readTree(category);
			for(int i=0;i<cNode.size();i++) {
				Category c = new Category();
				JsonNode cJson = cNode.get(Integer.toString(i));
				c.setCatId(cJson.get("id").asInt());
				c.setName(cJson.get("name").asText());
				c.setBudget(cJson.get("amount").asDouble());
				c.setBud(user.getBudget());
				cList.add(c);
				System.out.println(c);
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
			System.out.println("here");
		} 
		System.out.println(cList);
		if(!dList.isEmpty())
		{
			double spent = categoryService.deleteList(dList);
			user.getBudget().setTotalBudget(user.getBudget().getTotalBudget() + spent);
			user.getBudget().setTotalSpent(user.getBudget().getTotalSpent() - spent);
			req.getSession().setAttribute("user", user);
		}
		
		categoryService.mergeList(cList);
		
		return "budgetpage";
	}
	
}
