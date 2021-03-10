package com.delazari.traderisk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.delazari.traderisk.domain.Currency;
import com.delazari.traderisk.domain.Ema;

public class CurrencyDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pair;
	private double price;
	
	private List<Ema> emas = new ArrayList<>();
	
	public CurrencyDTO() {}

	public CurrencyDTO(Currency currency) {
		this.id = currency.getId();
		this.pair = currency.getPair();
		this.price = currency.getPrice();
		this.emas = currency.getEma();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Ema> getEmas() {
		return emas;
	}

	public void setEmas(List<Ema> emas) {
		this.emas = emas;
	}
}
