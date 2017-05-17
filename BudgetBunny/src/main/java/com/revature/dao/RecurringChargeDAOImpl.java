package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.revature.bean.RecurringCharge;

public class RecurringChargeDAOImpl implements RecurringChargeDAO {
	private Session session;
	
	@Override
	public RecurringCharge save(RecurringCharge r) {
		return null;
	}
	
	@Override
	public RecurringCharge getById(int id) {
		return null;
	}

	@Override
	public ArrayList<RecurringCharge> getALl() {
		return null;
	}
	
	@Override
	public RecurringCharge update(RecurringCharge r) {
		return null;
	}

	@Override
	public boolean delete(RecurringCharge r) {
		return false;
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

}
