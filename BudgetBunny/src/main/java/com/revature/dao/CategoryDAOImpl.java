package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Category> getAll() {
		String hql = "FROM Category";
		ArrayList<Category> cats = (ArrayList<Category>) session.createQuery(hql).list();
		return cats; 
	}

	@Override
	public void delete(Category c) {
		session.delete(c);
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

}
