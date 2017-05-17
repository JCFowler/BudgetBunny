package com.revature.dao;

import com.revature.bean.User;

public interface UserDAO extends HibernateSession{
	void save(User u);
	boolean isUsernameTaken(String username);
	User login(String uName, String pass);
}
