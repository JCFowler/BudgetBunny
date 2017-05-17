package com.revature.dao;

import com.revature.bean.Budget;

public interface BudgetDAO extends HibernateSession {
	//C
	Budget save(Budget b);
		
	//R
	Budget getById(int id);
	
	//U
	Budget update(Budget b);
	
	//D
}
