package com.revature.util;

import java.util.ArrayList;

import org.aspectj.lang.annotation.Aspect;

import com.revature.bean.RecurringCharge;
import com.revature.dao.RecurringChargeDAO;
import com.revature.dao.RecurringChargeDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

@Aspect
public class ProcessReocurringUtil implements Runnable {

	private static Thread processor;
	private Long interval;
	
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
		RecurringChargeDAO rc = new RecurringChargeDAOImpl();
		UserDAO u = new UserDAOImpl();
		System.out.println("DAO: " + rc);
		System.out.println("Processing: " + u);

//		RecurringCharge c = rc.getById(0);
		u.login("nope", "yep");
		ArrayList<RecurringCharge> charges = rc.getAllCharges();
		
				
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
	
	public static void start()
	{
		System.out.println("Starting: ");
		//if(processor == null)
		//{
			ProcessReocurringUtil runner = new ProcessReocurringUtil(5l);
			processor = new Thread(runner);
			processor.start();
		//}
	}
}
