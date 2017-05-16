package com.revature.dao;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
	private static Logger log = Logger.getRootLogger();
	private HibernateUtil hu = new HibernateUtil();

	@Override
	public User save(User u) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		su.save(u);
		
		tx.commit();
		su.close();
		log.info("Created new user: " + u.getName());
		return u;
	}
	
	@Override
	public boolean isUsernameTaken(String username) {
		Session su = hu.getSession();
		String hql = "FROM User WHERE username=:uName";
		Query q = (Query) su.createQuery(hql);
		q.setParameter("uName", username);
		int size = q.list().size();
		su.close();
		
		if(size > 0)
			return true;
		return false;
	}

	@Override
	public User login(String uName, String pass) {
		Session su = hu.getSession();
		String hql = "FROM User WHERE username=:uName and password=:pass";
		Query q = (Query) su.createQuery(hql);
		q.setParameter("uName", uName);
		q.setParameter("pass", pass);
		
		if(q.list().size() > 0)
		{
			User u = (User) q.list().get(0);
			log.info(u.getUsername() + " has Logged In. User's Budgetid is: " + u.getBudget().getBudgetId());
			su.close();
			return u;
		}
		else
		{
			su.close();
			return null;
		}
	}

	@Override
	public User getById(int id) {
		User u = null;
		Session su = hu.getSession();
		u = (User) su.get(User.class, id);
		su.close();
		return u;
	}
}
