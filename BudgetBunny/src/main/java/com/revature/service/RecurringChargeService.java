package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Budget;
import com.revature.bean.Category;
import com.revature.bean.RecurringCharge;
import com.revature.dao.BudgetDAO;
import com.revature.dao.RecurringChargeDAO;

@Service
public class RecurringChargeService {
	@Autowired
	RecurringChargeDAO rd;
	
	@Autowired
	BudgetDAO bd;
	
	public void saveList(ArrayList<RecurringCharge> rList) {
		for(RecurringCharge r : rList ) {
			rd.save(r);
		}
	}
	
	public Budget mergeBillList(ArrayList<RecurringCharge> rList, Budget b) {
		double totalSpent = 0, transactionSpent = 0;
		for(RecurringCharge r : rList ) {
			totalSpent += r.getCost();
			rd.merge(r);
		}
		for(Category c : b.getCategory() ) {
			transactionSpent += c.getSpent();
		}
		totalSpent -= transactionSpent;
		double initBudget = b.getTotalBudget() + b.getTotalSpent();
		System.out.println(totalSpent);
		b.setTotalSpent(totalSpent * -1);
		b.setTotalBudget(initBudget + totalSpent);
		
		bd.update(b);
		
		return b;
	}
	
	public Budget mergeIncomeList(ArrayList<RecurringCharge> rList, Budget b) {
		double totalBudget = 0;
		for(RecurringCharge r : rList ) {
			totalBudget += r.getCost();
			rd.merge(r);
		}
		b.setTotalBudget(totalBudget);
		
		bd.update(b);
		
		return b;
	}
	
	public void deleteList(ArrayList<Integer> dList) {
		for(Integer i : dList) {
			if(i != 0)
				rd.deleteById(i);
		}
	}
	
	public ArrayList<RecurringCharge> getAll(Budget b, boolean isIncome) {
		ArrayList<RecurringCharge> rList = rd.getAll(b);
		ArrayList<RecurringCharge> bList = new ArrayList<RecurringCharge>();
		ArrayList<RecurringCharge> iList = new ArrayList<RecurringCharge>();
		for(RecurringCharge r : rList) {
			if(r.getCost() > 0)
				iList.add(r);
			else
				bList.add(r);
		}
		if(isIncome)
			return iList;
		else 
			return bList;
	}
}
