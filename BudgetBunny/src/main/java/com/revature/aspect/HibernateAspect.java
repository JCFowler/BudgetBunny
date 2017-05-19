package com.revature.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.HibernateSession;
import com.revature.util.HibernateUtil;

@Component
@Aspect
public class HibernateAspect {
	
	@Autowired
	private HibernateUtil hu;
	@Around("allDaoObjects()")
	public Object manageSession(ProceedingJoinPoint pjp) {
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		HibernateSession dao = (HibernateSession) pjp.getThis();
		System.out.println("Asspect session: " + session);
		dao.setSession(session);
		//our return object
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			tx.rollback();
		}
		tx.commit();
		session.close();
		dao.setSession(null);
		return obj;
	}
	
	//Hook
	@Pointcut("execution(* com.revature.dao..*(..)) "
			+ "&& !execution(* com.revature.dao..setSession(..))")
	public void allDaoObjects(){}

}
