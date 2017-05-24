package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.Transaction;

@Component
public class TransactionDAOImpl implements TransactionDAO {
	private Session session;
	
	@Override
	public void save(Transaction t) {
		session.save(t);
	}

	@Override
	public Transaction getById(int id) {
		return (Transaction) session.get(Transaction.class, id);
	}

	@Override
	public void update(Transaction t) {
		session.update(t);
	}

	@Override
	public void delete(Transaction t) {
		session.delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Transaction> getAll(int id) {
		String hql = "FROM Transaction where cat.bud.budgetId=:id";
		Query q = session.createQuery(hql);
		q.setParameter("id", id );
		return (ArrayList<Transaction>) q.list(); 
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

	

	
}
