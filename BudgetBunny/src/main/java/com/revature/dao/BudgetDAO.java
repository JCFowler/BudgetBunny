package com.revature.dao;

import com.revature.bean.Budget;

public interface BudgetDAO extends HibernateSession {
	//C
	Budget save(Budget b);
		
	//R
	Budget getById(int id);
	Budget getByUserId(int UserId);
	
	//U
	void update(Budget b);
	
	//D
}
