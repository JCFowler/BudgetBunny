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
	
	public void saveAll(ArrayList<RecurringCharge> iList, ArrayList<RecurringCharge> bList, ArrayList<Category> cList, Budget b) {
		double totalBudget = 0;
		double totalSpent = 0;
		for(RecurringCharge r : iList ) {
			System.out.println("first");
			System.out.println("Here " + r);
			totalBudget += totalBudget + r.getCost();
			rd.save(r);
			System.out.println("ast");
		}
		
//		for(RecurringCharge r : bList ) {
//			totalSpent += totalSpent + r.getCost();
//			rd.save(r);
//		}
//
//		for(Category c : cList) {
//			cd.save(c);
//		}
//		
//		b.setTotalBudget(totalBudget);
//		b.setTotalSpent(totalSpent);
//		bd.update(b);	
	}
	
	public Budget get(int UserId) {
		return bd.getByUserId(UserId);
	}
}
