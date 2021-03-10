package com.delazari.traderisk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delazari.traderisk.domain.Currency;
import com.delazari.traderisk.domain.repository.CurrencyRepository;
import com.delazari.traderisk.dto.CurrencyDTO;
import com.delazari.traderisk.services.exception.ObjectNotFoundException;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	/**
	 * 
	 * @return
	 */
	public List<Currency> findAll(){
		
		return currencyRepo.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Currency findByID(String id) {
		
		Currency currency = currencyRepo.findOneById(id);
		if(currency == null) {
			throw new ObjectNotFoundException("Object " + id + " is not found!");
		}
		return currency;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Currency findByPair(String pair) {
		
		Currency currency = currencyRepo.findOneByPairIgnoreCase(pair);
		if(currency == null) {
			throw new ObjectNotFoundException("Object " + pair + " is not found!");
		}
		return currency;
	}
	public Currency searchOneByIdOrPair(String id, String pair) {
		
		Currency currency = currencyRepo.searchOneByIdOrPair(id, pair);
		if(currency == null) {
			throw new ObjectNotFoundException("Object " + pair + " is not found!");
		}
		return currency;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Currency insert(Currency obj) {
		
		return currencyRepo.insert(obj);
	}
	/**
	 * 
	 * @param id
	 */
	public void delete(String id) {
		
		findByID(id);
		currencyRepo.deleteById(id);
	}
	/**
	 * 
	 * @return
	 */
	public Currency update(Currency obj) {
		
		Currency newObj = findByID(obj.getId());
		updateData(newObj, obj);
		return currencyRepo.save(newObj);
	}
	/**
	 * 
	 * @param newObj
	 * @param obj
	 */
	private void updateData(Currency newObj, Currency obj) {
		
		newObj.setPair(obj.getPair());
		newObj.setPrice(obj.getPrice());
	}
	/**
	 * 
	 * @param objDt
	 * @return
	 */
	public Currency fromDTO(CurrencyDTO objDto) {
		
		return new Currency(objDto.getId(), objDto.getPair(), objDto.getPrice(), objDto.getEmas());
	}
}
