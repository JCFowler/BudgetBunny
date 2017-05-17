package com.revature.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.revature.bean.Transaction;

public class TransactionDAOImpl implements TransactionDAO {
	private Session session;
	
	@Override
	public Transaction save(Transaction t) {
		return null;
	}

	@Override
	public Transaction getById(int id) {
		return null;
	}

	@Override
	public Transaction update(Transaction t) {
		return null;
	}

	@Override
	public boolean delete(Transaction t) {
		return false;
	}

	@Override
	public ArrayList<Transaction> getAll() {
		return null;
	}

	@Override
	public void setSession(Session session) {
		this.session = session;
	}

}
