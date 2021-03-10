package com.delazari.traderisk.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ema")
public class Ema implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private TimeStatus time;
	private int calculate;
	private double price;
	
	
	public Ema() {}

	public Ema(String id, TimeStatus time, int calculate, double price) {
		this.id = id;
		this.time = time;
		this.price = price;
		this.calculate = calculate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TimeStatus getTime() {
		return time;
	}

	public void setTime(TimeStatus time) {
		this.time = time;
	}
	
	public int getCalculate() {
		return calculate;
	}

	public void setCalculate(int calculate) {
		this.calculate = calculate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Ema other = (Ema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
