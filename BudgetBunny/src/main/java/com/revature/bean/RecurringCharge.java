package com.revature.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="recurringcharge")
public class RecurringCharge {
	@Id
	@Column
	private int chargeId;
	private double cost;
	private String name;
	private int timeInterval;
	private LocalDate startDate;
	private LocalDate lastTransactionDate;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="unitid")
	private TimeUnit timeUnit;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="budgetid")
	private Budget bud;
	
	public RecurringCharge(int chargeId, double cost, int timeInterval, LocalDate startDate,
			LocalDate lastTransactionDate, TimeUnit timeUnit) {
		super();
		this.chargeId = chargeId;
		this.cost = cost;
		this.timeInterval = timeInterval;
		this.startDate = startDate;
		this.lastTransactionDate = lastTransactionDate;
		this.timeUnit = timeUnit;
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
	public int getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(LocalDate lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chargeId;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lastTransactionDate == null) ? 0 : lastTransactionDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + timeInterval;
		result = prime * result + ((timeUnit == null) ? 0 : timeUnit.hashCode());
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
		if (chargeId != other.chargeId)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (lastTransactionDate == null) {
			if (other.lastTransactionDate != null)
				return false;
		} else if (!lastTransactionDate.equals(other.lastTransactionDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (timeInterval != other.timeInterval)
			return false;
		if (timeUnit == null) {
			if (other.timeUnit != null)
				return false;
		} else if (!timeUnit.equals(other.timeUnit))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RecurringCharge [chargeId=" + chargeId + ", cost=" + cost + ", timeInterval=" + timeInterval
				+ ", startDate=" + startDate + ", lastTransactionDate=" + lastTransactionDate + ", timeUnit=" + timeUnit
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
