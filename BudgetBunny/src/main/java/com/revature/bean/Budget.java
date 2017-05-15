package com.revature.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="budget")
public class Budget {
	@Id
	@Column
	@SequenceGenerator(name="budget_seq", sequenceName="budget_seq")
	@GeneratedValue(generator="budget_seq", strategy=GenerationType.AUTO)
	private int budgetId;
	private double totalBudget;
	private double totalSpent;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="bud")
	private Collection<Category> category;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="bud")
	private Collection<RecurringCharge> recurringCharge;
	public int getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}
	public double getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}
	public double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}
	public Collection<Category> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}
	public Collection<RecurringCharge> getRecurringCharge() {
		return recurringCharge;
	}
	public void setRecurringCharge(ArrayList<RecurringCharge> recurringCharge) {
		this.recurringCharge = recurringCharge;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + budgetId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((recurringCharge == null) ? 0 : recurringCharge.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalBudget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalSpent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (budgetId != other.budgetId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (recurringCharge == null) {
			if (other.recurringCharge != null)
				return false;
		} else if (!recurringCharge.equals(other.recurringCharge))
			return false;
		if (Double.doubleToLongBits(totalBudget) != Double.doubleToLongBits(other.totalBudget))
			return false;
		if (Double.doubleToLongBits(totalSpent) != Double.doubleToLongBits(other.totalSpent))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", totalBudget=" + totalBudget + ", totalSpent=" + totalSpent
				+ ", category=" + category + ", recurringCharge=" + recurringCharge + "]";
	}
	public Budget(int budgetId, double totalBudget, double totalSpent, ArrayList<Category> category,
			ArrayList<RecurringCharge> recurringCharge) {
		super();
		this.budgetId = budgetId;
		this.totalBudget = totalBudget;
		this.totalSpent = totalSpent;
		this.category = category;
		this.recurringCharge = recurringCharge;
	}
	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
