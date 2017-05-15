package com.revature.dao;

import com.revature.bean.Budget;

public interface BudgetDAO {
	//C
	Budget save(Budget b);
		
	//R
	Budget getById(int id);
	
	//U
	Budget update(Budget b);
	
	//D
}
