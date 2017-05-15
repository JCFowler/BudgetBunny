package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.Transaction;

public interface TransactionDAO {
	//C
	Transaction save(Transaction t);
	
	//R
	Transaction getById(int id);
	ArrayList<Transaction> getAll();
	
	//U
	Transaction update(Transaction t);
	
	//D
	boolean delete(Transaction t);
}
