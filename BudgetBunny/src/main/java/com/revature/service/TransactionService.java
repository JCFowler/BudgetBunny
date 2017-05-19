package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Category;
import com.revature.bean.Transaction;
import com.revature.dao.BudgetDAO;
import com.revature.dao.CategoryDAO;
import com.revature.dao.TransactionDAO;

@Service
public class TransactionService {
	
	@Autowired
	TransactionDAO td; 
	
	@Autowired
	CategoryDAO cd;
	
	@Autowired 
	BudgetDAO bd;
	
	public void save(Transaction t){
		td.save(t);
		double spent = t.getCost() + t.getCat().getSpent(); 
		t.getCat().setSpent(spent);
		cd.update(t.getCat());
		double total = t.getCat().getBud().getTotalBudget() + spent;
		t.getCat().getBud().setTotalBudget(total);
		bd.update(t.getCat().getBud());
	}
	
	public Transaction getById(int id){
		return td.getById(id);
	}
	
	public ArrayList<Transaction> getAll(Category c){
		return td.getAll(c);
	}
	
	void update(Transaction t){
		td.update(t);
	}
	
	void delete(Transaction t){
		td.delete(t);
	}
}
