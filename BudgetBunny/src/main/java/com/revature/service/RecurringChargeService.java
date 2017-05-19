package com.revature.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.bean.RecurringCharge;
import com.revature.dao.RecurringChargeDAO;

@Service
public class RecurringChargeService {
	@Autowired
	RecurringChargeDAO rd;
	
	public void saveList(ArrayList<RecurringCharge> rList) {
		for(RecurringCharge r : rList ) {
			rd.save(r);
		}
	}
}
