package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timeUnit")
public class TimeUnit {
	@Id
	@Column
	private int unitId;
	private String unit;
	public TimeUnit(int unitId, String unit) {
		super();
		this.unitId = unitId;
		this.unit = unit;
	}
	public TimeUnit() {
		super();
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + unitId;
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
		TimeUnit other = (TimeUnit) obj;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (unitId != other.unitId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TimeUnit [unitId=" + unitId + ", unit=" + unit + "]";
	}
}
