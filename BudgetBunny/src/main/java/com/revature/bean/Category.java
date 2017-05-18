package com.revature.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column
	@SequenceGenerator(name="category_seq", sequenceName="category_seq")
	@GeneratedValue(generator="category_seq", strategy=GenerationType.AUTO)
	private int catId;
	private String name;
	private double budget;
	private double spent;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cat")
	private Collection<Transaction> transaction;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="budgetid")
	private Budget bud;

	public Category(int catId, String name, double budget, double spent, Collection<Transaction> transaction,
			Budget bud) {
		super();
		this.catId = catId;
		this.name = name;
		this.budget = budget;
		this.spent = spent;
		this.transaction = transaction;
		this.bud = bud;
	}

	public Category(int catId, String name, double budget, double spent, Budget bud) {
		super();
		this.catId = catId;
		this.name = name;
		this.budget = budget;
		this.spent = spent;
		this.bud = bud;
	}



	public Category() {
		super();
	}
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public double getSpent() {
		return spent;
	}
	public void setSpent(double spent) {
		this.spent = spent;
	}
	public Collection<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(ArrayList<Transaction> transaction) {
		this.transaction = transaction;
	}
	public Budget getBud() {
		return bud;
	}
	public void setBud(Budget bud) {
		this.bud = bud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(budget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + catId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(spent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
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
		Category other = (Category) obj;
		if (Double.doubleToLongBits(budget) != Double.doubleToLongBits(other.budget))
			return false;
		if (catId != other.catId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(spent) != Double.doubleToLongBits(other.spent))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Category [catId=" + catId + ", name=" + name + ", budget=" + budget + ", spent=" + spent
				+ ", transaction=" + transaction + "]";
	}
}
