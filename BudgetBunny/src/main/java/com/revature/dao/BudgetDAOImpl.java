package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Budget;
import com.revature.util.HibernateUtil;

public class BudgetDAOImpl implements BudgetDAO {
	private static Logger log = Logger.getRootLogger();
	private HibernateUtil hu = new HibernateUtil();
	
	@Override
	public Budget save(Budget b) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		int id = (Integer) su.save(b);
		
		b.setBudgetId(id);
		tx.commit();
		su.close();
		log.info("Created new Budget: " + b.getBudgetId());
		return b;
	}

	@Override
	public Budget getById(int id) {
		Budget b = null;
		Session su = hu.getSession();
		b = (Budget) su.get(Budget.class, id);
		su.close();
		return b;
	}

	@Override
	public Budget update(Budget b) {
		// TODO Auto-generated method stub
		return null;
	}

}
