package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value="/login")
public class LoginController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin(ModelMap modelMap)
	{
		User emptyUser = new User();
		modelMap.addAttribute("user", emptyUser);
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
		req.getSession().setAttribute("user", authUser);
		modelMap.addAttribute("user", authUser);
		return "redirect:home";
	}
}
