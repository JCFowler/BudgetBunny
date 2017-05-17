package com.revature.dao;

import java.util.ArrayList;

import com.revature.bean.RecurringCharge;

public interface RecurringChargeDAO extends HibernateSession{
	//C
	RecurringCharge save(RecurringCharge r);
	
	//R
	RecurringCharge getById(int id);
	ArrayList<RecurringCharge> getALl();
	
	//U
	RecurringCharge update(RecurringCharge r);
	
	//D
	boolean delete(RecurringCharge r);
}
