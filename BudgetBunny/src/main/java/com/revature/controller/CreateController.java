package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value="/create")
public class CreateController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCreatePage(ModelMap modelMap)
	{
		User emptyUser = new User();
		modelMap.addAttribute("createUser", emptyUser);
		return "create";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postCreate(@Valid User user, BindingResult bindingResult, HttpServletRequest req, ModelMap modelMap)
	{
		System.out.println("Posting");
		if(bindingResult.hasErrors())
		{
			return "create";
		}
		if(!userService.isUsernameTaken(user.getUsername()))
		{
			userService.create(user);
		}
		else {
			System.out.println("Username is taken");
			return "create";
		}
		return "create";
	}
}
