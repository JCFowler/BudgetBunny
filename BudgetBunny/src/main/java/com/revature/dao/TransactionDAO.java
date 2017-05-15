package com.revature.dao;

import com.revature.bean.Transaction;

public interface TransactionDAO {
	//C
	Transaction save(Transaction t);
	
	//R
	Transaction getById(int id);
	
	//U
	Transaction update(Transaction t);
	
	//D
	boolean delete(Transaction t);
}
