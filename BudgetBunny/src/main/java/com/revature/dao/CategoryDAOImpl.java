package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Category;
import com.revature.util.HibernateUtil;

public class CategoryDAOImpl implements CategoryDAO {
	private HibernateUtil hu = new HibernateUtil();
	private Logger log = Logger.getRootLogger();
	
	@Override
	public Category save(Category c) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		int id = (Integer)su.save(c);
		log.info("Created new transaction. id: " + id);
		c.setCatId(id);
		tx.commit();
		su.close();
		return c;
	}
	
	@Override
	public Category getById(int id) {
		Session su = hu.getSession();
		Category c = (Category) su.get(Category.class, id);
		su.close();
		return c;
	}

	@Override
	public Category update(Category c) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		su.update(c);
		log.info("Updated Categoryid: " + c.getCatId());
		tx.commit();
		su.close();
		return c;
	}

	@Override
	public ArrayList<Category> getAll(int budgetId) {
		Session su = hu.getSession();
		String hql = "From Category where budgetid=:bId";
		Query q = (Query) su.createQuery(hql);
		q.setParameter("bId", budgetId);
		List<Category> list = q.list();
		ArrayList<Category> arrayList = new ArrayList<Category>();
		arrayList.addAll(list);
		return arrayList;
	}

	@Override
	public boolean delete(Category c) {
		Session su = hu.getSession();
		Transaction tx = su.beginTransaction();
		su.delete(c);
		tx.commit();
		su.close();
		return true;
	}

}
