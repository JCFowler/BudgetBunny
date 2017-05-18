package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Category;
import com.revature.dao.CategoryDAO;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDAO cd;
	
	public void save(Category c){
		cd.save(c);
	}
	
	public Category getById(int id){
		return cd.getById(id);
	}
	
	public ArrayList<Category> getAll(){
		return cd.getAll();
	}

	void update(Category c){
		cd.update(c);
	}
	
	void delete(Category c){
		cd.delete(c);
	}
}
