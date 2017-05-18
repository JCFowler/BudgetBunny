package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.Transaction;

public interface TransactionDAO extends HibernateSession{
	//C
	void save(Transaction t);
	
	//R
	Transaction getById(int id);
	ArrayList<Transaction> getAll();
	
	//U
	void update(Transaction t);
	
	//D
	void delete(Transaction t);
}
