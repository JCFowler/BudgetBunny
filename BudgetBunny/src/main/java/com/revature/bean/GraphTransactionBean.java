package com.revature.bean;

import java.util.HashMap;
import java.util.Set;

public class GraphTransactionBean {

	private String title;
	private HashMap<String, HashMap<String, Double>> types = new HashMap<String, HashMap<String, Double>>();

	public void addTransaction(Transaction trans)
	{
		String typeName = trans.getCat().getName();
		HashMap<String, Double> map = types.get(typeName);
				
		if(map == null)
		{
			map = new HashMap<String, Double>();
			types.put(typeName, map);
		}
		
		
		String date = trans.getDate().getYear() + "-" + trans.getDate().getMonth();
		Double val = map.get(date);
		
		if(val == null)
			val = 0.0;
		
		if(date != null)
			map.put(date, trans.getCost() + val);
		
	}
	
	public String getTitle() {
		return title;
	}

	public HashMap<String, HashMap<String, Double>> getTypes() {
		return types;
	}
}
