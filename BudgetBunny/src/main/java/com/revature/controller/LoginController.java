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
import com.revature.util.ProcessReocurringUtil;

@Controller
@RequestMapping(value="/login")
public class LoginController
{
	@Autowired
	private ProcessReocurringUtil processUtil;
	@Autowired
	private UserService userService;
	
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
			Set<Category> catSet = new HashSet<Category>(authUser.getBudget().getCategory());
			Set<RecurringCharge> reSet = new HashSet<RecurringCharge>(authUser.getBudget().getRecurringCharge());
			ArrayList<Category> catList = new ArrayList<Category>(catSet);
			ArrayList<RecurringCharge> reList = new ArrayList<RecurringCharge>(reSet);
			authUser.getBudget().setCategory(catList);
			authUser.getBudget().setRecurringCharge(reList);
			
			double totalS = 0, totalB= 0;
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
			
//			System.out.println("User Authorized>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			gd.GenerateTransactions(authUser);
//			
			return null;
		}
	}
}
