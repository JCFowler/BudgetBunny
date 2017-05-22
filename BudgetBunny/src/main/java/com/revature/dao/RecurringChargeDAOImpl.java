package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.Budget;
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
	public ArrayList<RecurringCharge> getAllCharges() {
		String hql = "FROM RecurringCharge";
		System.out.println("My session: " + session);
		ArrayList<RecurringCharge> rcs = (ArrayList<RecurringCharge>) session.createQuery(hql).list();
		return rcs; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RecurringCharge> getAll(Budget b) {
		System.out.println("Here");
		System.out.println(b.getBudgetId());
		String hql = "FROM RecurringCharge WHERE bud = :budget";
		Query q = (Query) session.createQuery(hql);
		q.setParameter("budget", b);
		ArrayList<RecurringCharge> rList = (ArrayList<RecurringCharge>) q.list();
		return rList; 
		
	}
	
	@Override
	public void update(RecurringCharge r) {
		session.update(r);
	}
	
	@Override
	public void merge(RecurringCharge r) {
		session.merge(r);
	}

	@Override
	public void deleteById(int rId) {
		RecurringCharge r = (RecurringCharge)session.load(RecurringCharge.class, rId);
		session.delete(r);
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
