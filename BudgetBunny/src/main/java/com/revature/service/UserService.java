package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Budget;
import com.revature.bean.User;
import com.revature.dao.BudgetDAO;
import com.revature.dao.UserDAO;

@Service
public class UserService
{
	@Autowired
	UserDAO ud;
	@Autowired
	BudgetDAO bd;
	
	public boolean isUsernameTaken(String username) {
		return ud.isUsernameTaken(username);
	}
	
	public User login(String uName, String pass) {
		System.out.println("here " +uName + " " + pass);
		return ud.login(uName, pass);
	}
	
	public void create(User user) {
		Budget b = bd.save(new Budget(0,0,0));
		user.setBudget(b);
		ud.save(user);
	}
}
