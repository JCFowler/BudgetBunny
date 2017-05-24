package com.revature.bean;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

public class GraphTransactionBean {

	private String title;
	private SortedMap<String, SortedMap<String, Double>> types = new TreeMap<String, SortedMap<String, Double>>();

	public void addTransaction(Transaction trans)
	{
		String typeName = trans.getCat().getName();
		SortedMap<String, Double> map = types.get(typeName);
				
		if(map == null)
		{
			map = new TreeMap<String, Double>();
			types.put(typeName, map);
		}
		
		LocalDate ld = trans.getDate().toLocalDate();
		
		String date = ld.getYear() + "-" + ld.getMonthValue();
		Double val = map.get(date);
		
		if(val == null)
			val = 0.0;
		
		if(date != null)
			map.put(date, trans.getCost() + val);
		
	}
	
	public String getTitle() {
		return title;
	}

	public SortedMap<String, SortedMap<String, Double>> getTypes() {
		return types;
	}
}
