package com.delazari.traderisk.dto;

import java.io.Serializable;

import com.delazari.traderisk.domain.Ema;
import com.delazari.traderisk.domain.TimeStatus;

public class EmaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private TimeStatus time;
	private int calculate;
	private double price;
	
	public EmaDTO() {}

	public EmaDTO(Ema obj) {
		
		this.id = obj.getId();
		this.time = obj.getTime();
		this.calculate = obj.getCalculate();
		this.price = obj.getPrice();
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
}
