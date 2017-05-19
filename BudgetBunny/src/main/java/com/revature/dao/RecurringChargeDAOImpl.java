package com.revature.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.RecurringCharge;

@Component
public class RecurringChargeDAOImpl implements RecurringChargeDAO {
	private Session session;
	
	@Override
	public void save(RecurringCharge r) {
		session.save(r);	
	}
	
	@Override
	public RecurringCharge getById(int id) {
		RecurringCharge rc = (RecurringCharge) session.get(RecurringCharge.class, id);
		return rc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RecurringCharge> getAll() {
		String hql = "FROM RecurringCharge";
		ArrayList<RecurringCharge> rcs = (ArrayList<RecurringCharge>) session.createQuery(hql).list();
		return rcs; 
	}
	
	@Override
	public void update(RecurringCharge r) {
		session.update(r);
	}

	@Override
	public void delete(RecurringCharge r) {
		session.delete(r);;
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

}
