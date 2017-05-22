package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.Budget;
import com.revature.bean.Category;

@Component
public class CategoryDAOImpl implements CategoryDAO {
	private Session session;
	
	@Override
	public void save(Category c) {
		session.save(c);
	}
	
	@Override
	public Category getById(int id) {
		Category c = (Category) session.get(Category.class, id);
		return c;
	}

	@Override
	public void update(Category c) {
		session.update(c);
	}
	
	@Override
	public void merge(Category c) {
		session.merge(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Category> getAll(Budget b ) {
		String hql = "FROM Category WHERE bud=:id";
		Query q = session.createQuery(hql);
		q.setParameter("id", b);
		ArrayList<Category> cats = (ArrayList<Category>) q.list();
		return cats; 
	}

	@Override
	public void delete(Category c) {
		session.delete(c);
	}
	
	@Override
	public void deleteById(int catId) {
		Category c = (Category)session.load(Category.class, catId);
		session.delete(c);
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}
}
