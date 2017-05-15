package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.Category;

public interface CategoryDAO {
	//C
	Category save(Category c);
	
	//R
	Category getById(int id);
	ArrayList<Category> getAll();
	
	//U
	Category update(Category c);
	
	//D
	boolean delete(Category c);
}
