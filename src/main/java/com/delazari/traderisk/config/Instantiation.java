package com.delazari.traderisk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.delazari.traderisk.domain.Currency;
import com.delazari.traderisk.domain.Ema;
import com.delazari.traderisk.domain.TimeStatus;
import com.delazari.traderisk.domain.repository.CurrencyRepository;
import com.delazari.traderisk.domain.repository.EmaRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private CurrencyRepository currencyRep;
	
	@Autowired
	private EmaRepository emaRep;
	
	@Override
	public void run(String... args) throws Exception {
	
		currencyRep.deleteAll();
		
		Ema ema1 = new Ema(null, TimeStatus._1H, 9, 59870.56);
		Ema ema2 = new Ema(null, TimeStatus._1H, 20, 52341.85);
		Ema ema3 = new Ema(null, TimeStatus._1H, 80, 42002.11);
		emaRep.saveAll(Arrays.asList(ema1, ema2, ema3));
		Currency c1 = new Currency(null, "BTCUSDT", 55390.33, Arrays.asList(ema1, ema2, ema3));
		
		ema1 = new Ema(null, TimeStatus._4H, 8, 2200.06);
		ema2 = new Ema(null, TimeStatus._4H, 25, 1887.33);
		ema3 = new Ema(null, TimeStatus._4H, 80, 1212.11);
		emaRep.saveAll(Arrays.asList(ema1, ema2, ema3));
		Currency c2 = new Currency(null, "ETHUSDT", 1840.25, Arrays.asList(ema1, ema2, ema3));
		
		ema1 = new Ema(null, TimeStatus._1D, 12, 1.19);
		ema2 = new Ema(null, TimeStatus._1D, 25, 0.85);
		ema3 = new Ema(null, TimeStatus._1D, 99, 0.47);
		emaRep.saveAll(Arrays.asList(ema1, ema2, ema3));
		Currency c3 = new Currency(null, "ADAUSDT", 1.17, Arrays.asList(ema1, ema2, ema3));
		
		currencyRep.saveAll(Arrays.asList(c1, c2, c3));
		
	}
}
