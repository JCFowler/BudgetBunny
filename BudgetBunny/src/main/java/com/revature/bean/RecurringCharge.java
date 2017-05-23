package com.revature.bean;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="recurringcharge")
public class RecurringCharge {
	@Id
	@Column
	@SequenceGenerator(name="recurringcharge_seq", sequenceName="recurringcharge_seq")
	@GeneratedValue(generator="recurringcharge_seq", strategy=GenerationType.AUTO)
	private int chargeId;
	private double cost;
	private String name;
	private String isRecurring;
	private Date startDate;
	private Date lastTransactionDate;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="budgetid")
	private Budget bud;
	
	public RecurringCharge(int chargeId, double cost, String name, String isRecurring, Date startDate,
			Date lastTransactionDate) {
		super();
		this.chargeId = chargeId;
		this.cost = cost;
		this.name = name;
		this.isRecurring = isRecurring;
		this.startDate = startDate;
		this.lastTransactionDate = lastTransactionDate;
	}
	public RecurringCharge() {
		super();
	}
	public int getChargeId() {
		return chargeId;
	}
	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsRecurring() {
		return isRecurring;
	}
	public void setIsRecurring(String isRecurring) {
		this.isRecurring = isRecurring;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(Date lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
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
		result = prime * result + ((bud == null) ? 0 : bud.hashCode());
		result = prime * result + chargeId;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((isRecurring == null) ? 0 : isRecurring.hashCode());
		result = prime * result + ((lastTransactionDate == null) ? 0 : lastTransactionDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		RecurringCharge other = (RecurringCharge) obj;
		if (bud == null) {
			if (other.bud != null)
				return false;
		} else if (!bud.equals(other.bud))
			return false;
		if (chargeId != other.chargeId)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (isRecurring == null) {
			if (other.isRecurring != null)
				return false;
		} else if (!isRecurring.equals(other.isRecurring))
			return false;
		if (lastTransactionDate == null) {
			if (other.lastTransactionDate != null)
				return false;
		} else if (!lastTransactionDate.equals(other.lastTransactionDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RecurringCharge [chargeId=" + chargeId + ", cost=" + cost + ", name=" + name + ", isRecurring="
				+ isRecurring + ", startDate=" + startDate + ", lastTransactionDate=" + lastTransactionDate + "]";
	}
}
