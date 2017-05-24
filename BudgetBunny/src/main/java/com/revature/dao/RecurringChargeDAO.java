package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.Budget;
import com.revature.bean.RecurringCharge;

public interface RecurringChargeDAO extends HibernateSession{
	//C
	void save(RecurringCharge r);
	
	//R
	RecurringCharge getById(int id);
	RecurringCharge getByIdEager(int id);
	ArrayList<RecurringCharge> getAll(Budget b);
	ArrayList<RecurringCharge> getAllCharges();
	
	//U
	void update(RecurringCharge r);
	void merge(RecurringCharge r);
	
	//D
	void deleteById(int rId);
	void delete(RecurringCharge r);
}
