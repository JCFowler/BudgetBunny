package com.revature.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.User;

@Component
public class UserDAOImpl implements UserDAO {
	private Session session;

	@Override
	public void save(User u) {
		session.save(u);
	}
	
	@Override
	public boolean isUsernameTaken(String username) {
		String hql = "FROM User WHERE username=:uName";
		Query q = (Query) session.createQuery(hql);
		q.setParameter("uName", username);
		
		if(q.list().size() > 0)
			return true;
		return false;
	}

	@Override
	public User login(String uName, String pass) {
		String hql = "FROM User WHERE username=:uName and password=:pass";
		Query q = (Query) session.createQuery(hql);
		q.setParameter("uName", uName);
		q.setParameter("pass", pass);
		
		if(q.list().size() > 0)
		{
			User u = (User) q.list().get(0);
			return u;
		}
		else
			return null;
	}


	@Override
	public void setSession(Session session) {
		this.session = session;
	}
}
