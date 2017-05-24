package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Budget;
import com.revature.bean.Category;
import com.revature.bean.RecurringCharge;
import com.revature.dao.BudgetDAO;
import com.revature.dao.CategoryDAO;
import com.revature.dao.RecurringChargeDAO;

@Service
public class BudgetService {
	@Autowired
	BudgetDAO bd;
	
	@Autowired
	CategoryDAO cd;
	
	@Autowired
	RecurringChargeDAO rd;
	
	public Budget saveAll(ArrayList<RecurringCharge> iList, ArrayList<RecurringCharge> bList, ArrayList<Category> cList, Budget b) {
		double totalBudget = 0;
		double totalSpent = 0;
		for(RecurringCharge r : iList ) {
			totalBudget += totalBudget + r.getCost();
			rd.save(r);
		}
		
		for(RecurringCharge r : bList ) {
			totalSpent += totalSpent + r.getCost();
			rd.save(r);
		}

		for(Category c : cList) {
			cd.save(c);
		}
		totalBudget -= totalSpent;
		b.setTotalBudget(totalBudget);
		b.setTotalSpent(totalSpent);
		bd.update(b);	
		return b;
	}
	
	public Budget get(int UserId) {
		return bd.getByUserId(UserId);
	}

	public void save(Budget b) {
		bd.save(b);
	}
}
