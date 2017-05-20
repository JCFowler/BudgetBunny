package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Budget;
import com.revature.bean.User;
import com.revature.service.BudgetService;
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
	@Autowired
	private BudgetService budgetService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin(ModelMap modelMap)
	{
		User emptyUser = new User();
		modelMap.addAttribute("user", emptyUser);
		
		processUtil.start();
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, HttpServletRequest req, ModelMap modelMap)
	{
		if(bindingResult.hasErrors())
		{
			return "login";
		}
		User authUser = userService.login(user.getUsername(), user.getPassword());
		if(authUser==null)
			return "login";
		else {
			Budget b = budgetService.get(authUser.getUserId());
			req.getSession().setAttribute("user", authUser);
			if(authUser.getBudget().getTotalBudget() == 0)
				return "redirect:budgetsetuppage";
			else
			{
				req.getSession().setAttribute("budget", b);
				return "redirect:home";
			}
		}
	}
}
