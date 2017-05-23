package com.revature.bean;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
@Table(name="transaction")
public class Transaction {
	@Id
	@Column
	@SequenceGenerator(name="transaction_seq", sequenceName="transaction_seq")
	@GeneratedValue(generator="transaction_seq", strategy=GenerationType.AUTO)
	private int transactionId;
	@Column(name="transactionDate")
	private Date date;
	private double cost;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="catid")
	private Category cat;
	
	public Transaction(double cost, Category cat){
		super ();

		this.cost = cost;
		this.cat = cat;
		this.transactionId = 0;
		//CharSequence test = ;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.US );
		Date test = Date.valueOf(LocalDate.now());
		this.date = test;
	}

	public Transaction() {
		super();
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + transactionId;
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", date=" + date + ", cost=" + cost
				+ "]";
	}
}
