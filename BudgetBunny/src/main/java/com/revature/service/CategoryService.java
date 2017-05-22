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
	
	public void deleteList(ArrayList<Integer> catIds){
		for(Integer i : catIds) {
			if(i != 0)
				cd.deleteById(i);
		}
	}

	public void mergeList(ArrayList<Category> cList) {
		for(Category c : cList) {
			cd.merge(c);
		}
	}
}
