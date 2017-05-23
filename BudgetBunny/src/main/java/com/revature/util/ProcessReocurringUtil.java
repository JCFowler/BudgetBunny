package com.revature.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.bean.Budget;
import com.revature.bean.RecurringCharge;
import com.revature.dao.BudgetDAO;
import com.revature.dao.RecurringChargeDAO;
import com.revature.dao.UserDAO;

@Component
@Aspect
public class ProcessReocurringUtil implements Runnable {

	private Thread processor = null;
	private static ProcessReocurringUtil instance = null;
	private Long interval;
	
	@Autowired
	UserDAO ud;
	@Autowired
	RecurringChargeDAO rcd;
	@Autowired
	BudgetDAO bd;
	
	public static void main(String...args)
	{
		System.out.println(LocalTime.now());
		System.out.println(LocalDate.now());
	}
	
	private ProcessReocurringUtil()
	{
		super();
		interval = 60*60*4l;
	}	
	
	@Override
	public void run() {
		while(true)
		{
			ArrayList<RecurringCharge> charges = rcd.getAllCharges();

			for(RecurringCharge charge : charges)
			{
				processCharge(charge);
			}
			sleep();
		}
	}
	
	private void sleep()
	{
		try {
			Thread.sleep(interval * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void processCharge(RecurringCharge charge)
	{
		instance.processChargePrivate(charge);
	}
	
	private void processChargePrivate(RecurringCharge charge)
	{
		Date now = new Date();
		processCharge(charge, now);	
	}
	
	public void processOneTimeCharge(RecurringCharge charge)
	{
		Date d = new Date();
		d.setYear(d.getYear() + 100);
		processCharge(charge, d);
	}
	
	private void processCharge(RecurringCharge charge, Date now)
	{
		String seperator = "\n\n***************************************\n\n";
		System.out.println(seperator + "Processing Reocurring Transaction: \n" + charge + seperator);

		if(charge.getLastTransactionDate() == null)
		{
			charge.setLastTransactionDate(new Date());
		}
		Date last = charge.getLastTransactionDate();
		Date processDate = new Date(last.getTime());
		processDate.setMonth(processDate.getMonth() + 1);
		if(processDate.getTime() < now.getTime())
		{
			Budget b = charge.getBud();
			double amount = charge.getCost();
			if(amount > 0)
			{
				double total = b.getTotalBudget();
				b.setTotalBudget(total + amount);
			}
			else
			{
				double spent = b.getTotalSpent();
				b.setTotalSpent(spent + amount * -1);
			}
			bd.save(b);
			charge.setLastTransactionDate(processDate);

			rcd.update(charge);
		}
	}
	
	public void start()
	{
		if(processor == null)
		{
			processor = new Thread(this);
			instance = this;
			processor.start();
		}
	}
}
