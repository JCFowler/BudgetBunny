package com.revature.dao;

import java.util.ArrayList;

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
		Transaction t = (Transaction) session.get(Transaction.class, id);
		return t;
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
	public ArrayList<Transaction> getAll() {
		String hql = "FROM Transaction";
		ArrayList<Transaction> trans = (ArrayList<Transaction>) session.createQuery(hql).list();
		return trans; 
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

}
