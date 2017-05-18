package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Category;
import com.revature.bean.User;
import com.revature.service.CategoryService;

@Controller
@RequestMapping(value="/home")
public class HomeController
{
	@Autowired
	private CategoryService catService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHomepage(HttpServletRequest req)
	{		
		if(req.getSession().getAttribute("user") == null)
			return "redirect:login";
			
		else {
				User u = (User) req.getSession().getAttribute("user");
				List<Category> list = new ArrayList<Category>();
				list = catService.getAll(u.getBudget());
				System.out.println("LIST = " + list.size());
				req.getSession().setAttribute("cats", list);	
		}
		return "home";
	}
	
	
		
				
	

	
}
