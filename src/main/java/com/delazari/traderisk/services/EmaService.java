package com.delazari.traderisk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delazari.traderisk.domain.Ema;
import com.delazari.traderisk.domain.repository.EmaRepository;
import com.delazari.traderisk.dto.EmaDTO;
import com.delazari.traderisk.services.exception.ObjectNotFoundException;

@Service
public class EmaService {
	
	@Autowired
	private EmaRepository emaRepo;
	
	/**
	 * 
	 * @return
	 */
	public List<Ema> findAll(){
		
		return emaRepo.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Ema findByID(String id) {
		
		Ema ema = emaRepo.findOneById(id);
		if(ema == null) {
			throw new ObjectNotFoundException("Object " + id + " is not found!");
		}
		return ema;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Ema insert(Ema obj) {
		
		return emaRepo.insert(obj);
	}
	/**
	 * 
	 * @param id
	 */
	public void delete(String id) {
		
		findByID(id);
		emaRepo.deleteById(id);
	}
	/**
	 * 
	 * @return
	 */
	public Ema update(Ema obj) {
		
		Ema newObj = findByID(obj.getId());
		updateData(newObj, obj);
		return emaRepo.save(newObj);
	}
	/**
	 * 
	 * @param newObj
	 * @param obj
	 */
	private void updateData(Ema newObj, Ema obj) {
		
		newObj.setTime(obj.getTime());
		newObj.setCalculate(obj.getCalculate());
		newObj.setPrice(obj.getPrice());
	}
	/**
	 * 
	 * @param objDt
	 * @return
	 */
	public Ema fromDTO(EmaDTO objDto) {
		
		return new Ema(objDto.getId(), objDto.getTime(), objDto.getCalculate(), objDto.getPrice());
	}
}
