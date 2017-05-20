package com.revature.util;

import java.util.ArrayList;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.bean.RecurringCharge;
import com.revature.dao.BudgetDAO;
import com.revature.dao.RecurringChargeDAO;
import com.revature.dao.RecurringChargeDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

@Component
@Aspect
public class ProcessReocurringUtil implements Runnable {

	private static Thread processor;
	private Long interval;
	
	@Autowired
	UserDAO ud;
	@Autowired
	RecurringChargeDAO rcd;
	
	private ProcessReocurringUtil()
	{
		super();
		interval = 5l;
	}	
	
	private ProcessReocurringUtil(Long timeSeconds)
	{
		interval = timeSeconds;
	}
	
	@Override
	public void run() {
		System.out.println("DAO: " + rcd);
		System.out.println("Processing: " + ud);

//		RecurringCharge c = rc.getById(0);
		ud.login("nope", "yep");
		ArrayList<RecurringCharge> charges = rcd.getAllCharges();
		
				
		for(RecurringCharge charge : charges)
		{
			System.out.println("Processing: " + charge);
		}
		sleep();
	}
	
	private void sleep()
	{
		try {
			Thread.sleep(interval * 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean processCharge(RecurringCharge charge)
	{
		System.out.println(charge);
		return true;
	}
	
	public void start()
	{
		System.out.println("Starting: ");
		if(processor == null)
		{
			processor = new Thread(this);
			processor.start();
		}
	}
}
