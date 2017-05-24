package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.Budget;
import com.revature.bean.Category;
import com.revature.dao.CategoryDAO;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDAO cd;
	
	public void saveList(ArrayList<Category> cList) {
		for(Category c : cList) {
			cd.save(c);
		}
	}
	
	public void save(Category c){
		cd.save(c);
	}
	
	public Category getById(int id){
		return cd.getById(id);
	}
	
	public ArrayList<Category> getAll(Budget b){
		return cd.getAll(b);
	}

	void update(Category c){
		cd.update(c);
	}
	
	public double deleteList(ArrayList<Integer> catIds){
		double spent = 0;
		for(Integer i : catIds) {
			if(i != 0) 
			{
				Category c = cd.getById(i);
				spent += c.getSpent();
				cd.delete(c);
			}
		}
		return spent;
	}

	public void mergeList(ArrayList<Category> cList) {
		for(Category c : cList) {
			if(c.getCatId() != 0)
			{
				Category oldC = cd.getById(c.getCatId());
				c.setSpent(oldC.getSpent());
			}
			cd.merge(c);
		}
	}
}
