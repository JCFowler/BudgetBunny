package com.revature.dao;

import com.revature.bean.User;

public interface UserDAO {
	//C
	User save(User u);
	
	//R
	User login(User u);
	User getById(int id);
//	Bear getById(int i);
//	Bear loadById(int i);
	
	//U
//	Bear update(Bear b);
//	Bear merge(Bear b);
	//D
//	void delete(Bear b);
}
