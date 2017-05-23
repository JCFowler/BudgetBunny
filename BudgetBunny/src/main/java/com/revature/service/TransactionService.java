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
	
	public void save(Transaction t, int catId){
		
		Category c = cd.getById(catId);
		t.setCat(c);
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
	
	public ArrayList<Transaction> getAll(int id){
		return td.getAll(id);
	}
	
	public void update(Transaction t){
		td.update(t);
	}
	
	public void delete(ArrayList<Integer> transIds){
		for(Integer i : transIds){
			if(i != 0)
				td.delete(i);
		}
	}
}
