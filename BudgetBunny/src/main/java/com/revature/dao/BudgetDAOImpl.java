package com.revature.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.bean.Budget;

@Component
public class BudgetDAOImpl implements BudgetDAO {
	private Session session; 
	
	@Override
	public Budget save(Budget b) {
		int id = (Integer) session.save(b);
		b.setBudgetId(id);
		return b;
	}

	@Override
	public Budget getById(int id) {
		return (Budget) session.get(Budget.class, id);
	}

	@Override
	public void update(Budget b) {
		session.update(b);
	}

	@Override
	public void setSession(Session session) {
		this.session=session;
	}

	@Override
	public Budget getByUserId(int UserId) {
		String hql = "FROM Budget where userid=:uId";
		Query q = (Query) session.createQuery(hql);
		q.setParameter("uId", UserId);
		
		return (Budget) q.list().get(0);
	}

}
