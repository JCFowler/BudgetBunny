package com.revature.dao;

import com.revature.bean.User;

public interface UserDAO {
	//C
	User save(User u);
	
	//R
	boolean isUsernameTaken(String username);
	User login(String uName, String pass);
	User getById(int id);
	
	//U
	
	//D
}
