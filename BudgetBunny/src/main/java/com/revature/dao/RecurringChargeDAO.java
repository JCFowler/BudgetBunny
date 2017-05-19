package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.RecurringCharge;

public interface RecurringChargeDAO extends HibernateSession{
	//C
	void save(RecurringCharge r);
	
	//R
	RecurringCharge getById(int id);
	ArrayList<RecurringCharge> getAllCharges();
	
	//U
	void update(RecurringCharge r);
	
	//D
	void delete(RecurringCharge r);
}
