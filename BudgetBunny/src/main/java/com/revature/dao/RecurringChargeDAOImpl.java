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
		return (RecurringCharge) session.get(RecurringCharge.class, id);
	}
	
	@Override
	public RecurringCharge getByIdEager(int id) {
		return (RecurringCharge) session.get(RecurringCharge.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RecurringCharge> getAllCharges() {
		String hql = "FROM RecurringCharge";
		return  (ArrayList<RecurringCharge>) session.createQuery(hql).list();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<RecurringCharge> getAll(Budget b) {
		String hql = "FROM RecurringCharge WHERE bud = :budget";
		Query q = (Query) session.createQuery(hql);
		q.setParameter("budget", b);
		return (ArrayList<RecurringCharge>) q.list();
		 		
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
		session.delete(r);
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}
}
