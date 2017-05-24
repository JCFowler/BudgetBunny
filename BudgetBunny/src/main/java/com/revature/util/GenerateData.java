package com.revature.util;

import java.sql.Date;
import java.util.Collection;
import java.util.Random;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.bean.Category;
import com.revature.bean.Transaction;
import com.revature.bean.User;
import com.revature.dao.BudgetDAO;
import com.revature.dao.UserDAO;
import com.revature.service.TransactionService;

@Component
@Aspect
public class GenerateData {

	@Autowired
	UserDAO ud;
	@Autowired
	BudgetDAO bd;
	@Autowired
	TransactionService tService;
	Random r = new Random();
	
	public void generateTransactions(User u)
	{
		int max = 50;
		for(int i = 0; i < max; i++)
		{
			Collection<Category> cats = u.getBudget().getCategory();
			double cost = Math.random() * 500;
			
			int year = 2017;
			int month = (int) (r.nextInt() % 12 + 1);
			int day = (int) (r.nextInt() % 28 + 1);
			Date date = Date.valueOf(year + "-" + month + "-" + day);
						
			int stop = (int) (r.nextInt() % cats.size());
			int count = 0;
			

			Category chosen = null;
			for(Category c : cats){
				chosen = c;
				if(count++ == stop)
					break;
			}
			
			if(chosen != null)
				this.tService.save(createTransaction(cost, chosen, date), chosen.getCatId());
		}
		
	}
	
	public Transaction createTransaction(double cost, Category cat, Date d)
	{
		Transaction t = new Transaction(cost, cat);
		t.setDate(d);
				
		return t;
	}
}
