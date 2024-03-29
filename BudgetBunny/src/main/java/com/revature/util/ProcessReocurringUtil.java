package com.revature.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.bean.Budget;
import com.revature.bean.RecurringCharge;
import com.revature.service.BudgetService;
import com.revature.service.RecurringChargeService;

@Component
@Aspect
public class ProcessReocurringUtil implements Runnable {

	private Thread processor = null;
	private static ProcessReocurringUtil instance = null;
	private Long interval;
	private Logger log = Logger.getRootLogger();
	private int falures = 0;
	private int failMax = 10;
	
	@Autowired
	RecurringChargeService rcs;
	@Autowired
	BudgetService bs;
	
    private ProcessReocurringUtil()
    {
        super();
        interval = 30l;
    }   
    
    @Override
    public void run() {
        while(true)
        {
            ArrayList<RecurringCharge> charges = rcs.getAll();
            for(RecurringCharge charge : charges)
            {
                String brake = "\n*****************************************\n";
                System.out.println(brake + "\nProccessing Charge: " + charge  + "\n" + brake);
                processCharge(charge);
            }
            String brake = "\n*****************************************\n";
            System.out.println(brake + "Proccessing Complete" + brake);
            sleep();
        }
    }
	
	private void sleep()
	{
		try {
			Thread.sleep(interval * 1000);
			falures = 0;
		} catch (InterruptedException e) {
			log.error(e);
			falures++;
			if(falures > failMax)
				this.processor.interrupt();
		}
	}
	
	public static void processCharge(RecurringCharge charge)
	{
		instance.processChargePrivate(charge);
	}
	
	private void processChargePrivate(RecurringCharge charge)
	{
		processCharge(charge, LocalDate.now());	
	}
	
	public void processOneTimeCharge(RecurringCharge charge)
	{
		LocalDate ld = LocalDate.now();
		ld.plusYears(100);
		processCharge(charge, ld);
	}
	
	private void processCharge(RecurringCharge charge, LocalDate now)
	{
		if(charge.getLastTransactionDate() == null)
		{
			charge.setLastTransactionDate(new Date());
		}
		
		LocalDate processDate = new java.sql.Date( new java.util.Date().getTime() ).toLocalDate();
		processDate = processDate.plusMonths(1);
		
		if(processDate.isBefore(now))
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
			bs.save(b);
			charge.setLastTransactionDate(java.sql.Date.valueOf(processDate));

			rcs.update(charge);
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
