package com.revature.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Category;
import com.revature.bean.RecurringCharge;
import com.revature.bean.User;
import com.revature.service.UserService;
import com.revature.util.GenerateData;
import com.revature.util.ProcessReocurringUtil;

@Controller
@RequestMapping(value="/login")
public class LoginController
{
	@Autowired
	private ProcessReocurringUtil processUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private GenerateData gd;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin(ModelMap modelMap)
	{
		User emptyUser = new User();
		modelMap.addAttribute("user", emptyUser);
		
		processUtil.start();
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, HttpServletResponse resp, HttpServletRequest req, ModelMap modelMap)
	{
		if(bindingResult.hasErrors())
		{
			return "login";
		}
		User authUser = userService.login(user.getUsername(), user.getPassword());
		if(authUser==null) {
			resp.setStatus(401);
			return null;
		}
		else {
			Set<Category> catSet = new HashSet<>(authUser.getBudget().getCategory());
			Set<RecurringCharge> reSet = new HashSet<>(authUser.getBudget().getRecurringCharge());
			ArrayList<Category> catList = new ArrayList<>(catSet);
			ArrayList<RecurringCharge> reList = new ArrayList<>(reSet);
			authUser.getBudget().setCategory(catList);
			authUser.getBudget().setRecurringCharge(reList);
			
			double totalS = 0;
			double totalB= 0;
			for(Category c : authUser.getBudget().getCategory())
				totalS += c.getSpent();
			
			for(RecurringCharge r : authUser.getBudget().getRecurringCharge()) {
				if(r.getCost() > 0)
					totalB += r.getCost();
				else
					totalS -= r.getCost(); 
			}
			
			totalB -= totalS;
			authUser.getBudget().setTotalBudget(totalB);
			authUser.getBudget().setTotalSpent(totalS);
			
			req.getSession().setAttribute("user", authUser);
			
			//gd.generateTransactions(authUser);
			return null;
		}
	}
}
