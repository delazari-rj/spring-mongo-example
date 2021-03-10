package com.delazari.traderisk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.delazari.traderisk.domain.Currency;
import com.delazari.traderisk.domain.Ema;
import com.delazari.traderisk.dto.CurrencyDTO;
import com.delazari.traderisk.services.CurrencyService;

@RestController
@RequestMapping(value="/currencies")
public class CurrencyResource {
	
	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping
	public ResponseEntity<List<CurrencyDTO>> findAll(){
		
		List<Currency> list = currencyService.findAll();
		List<CurrencyDTO> listDto = list.stream().map(x -> new CurrencyDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> findById(@PathVariable String id){
		
		return ResponseEntity.ok().body(new CurrencyDTO(currencyService.findByID(id)));
	}
	
	@RequestMapping(value="/{id}/pair", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> findByPair(@PathVariable String id){
		
		return ResponseEntity.ok().body(new CurrencyDTO(currencyService.findByPair(id)));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CurrencyDTO objDto){
		
		Currency obj = currencyService.fromDTO(objDto);
		obj = currencyService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id{}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody String id){
		
		currencyService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody String id, @RequestBody CurrencyDTO objDto){
		
		Currency obj = currencyService.fromDTO(objDto);
		obj.setId(id);
		obj = currencyService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/emasearch", method = RequestMethod.GET)
	public ResponseEntity<List<Ema>> findEmasById(@RequestParam(value = "id", defaultValue = "") String id){
		
		return ResponseEntity.ok().body(currencyService.findByID(id).getEma());
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> searchOneByIdOrPair(
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "pair", defaultValue = "") String pair){
		
		return ResponseEntity.ok().body(new CurrencyDTO(currencyService.searchOneByIdOrPair(id,pair)));
	}
}
