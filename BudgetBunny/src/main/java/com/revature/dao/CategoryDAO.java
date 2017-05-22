package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.Budget;
import com.revature.bean.Category;

public interface CategoryDAO extends HibernateSession{
	//C
	void save(Category c);
	
	//R
	Category getById(int id);
	ArrayList<Category> getAll(Budget b);
	
	//U
	void update(Category c);
	void merge(Category c);
	
	//D
	void delete(Category c);
	void deleteById(int catId);
}
