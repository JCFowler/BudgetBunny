package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Budget;
import com.revature.util.HibernateUtil;

public class BudgetDAOImpl implements BudgetDAO {
	private HibernateUtil hu = new HibernateUtil();
	private Session session; 
	
	@Override
	public Budget save(Budget b) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		int id = (Integer) su.save(b);
		
		b.setBudgetId(id);
		tx.commit();
		su.close();
		return b;
	}

	@Override
	public Budget getById(int id) {
		Session su = hu.getSession();
		Budget b = (Budget) su.get(Budget.class, id);
		su.close();
		return b;
	}

	@Override
	public Budget update(Budget b) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		su.update(b);
		tx.commit();
		su.close();
		return b;
	}

	@Override
	public void setSession(Session session) {
		this.session=session;
	}

}
