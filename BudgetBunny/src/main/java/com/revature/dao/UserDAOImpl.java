package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
	private static Logger log = Logger.getRootLogger();
	private HibernateUtil hu = new HibernateUtil();

	@Override
	public User save(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(int id) {
		System.out.println("Started");
		User u = null;
		Session su = hu.getSession();
		System.out.println("Got Session");
		u = (User) su.load(User.class, id);
		System.out.println("Mapped User");
//		su.close();
		System.out.println("Finish");
		return u;
	}


}
