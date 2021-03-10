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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.delazari.traderisk.domain.Ema;
import com.delazari.traderisk.dto.EmaDTO;
import com.delazari.traderisk.services.EmaService;

@RestController
@RequestMapping(value="/emas")
public class EmaResource {
	
	@Autowired
	private EmaService emaService;
	
	@GetMapping
	public ResponseEntity<List<EmaDTO>> findAll(){
		
		List<Ema> list = emaService.findAll();
		List<EmaDTO> listDto = list.stream().map(x -> new EmaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmaDTO> findById(@PathVariable String id){
		
		return ResponseEntity.ok().body(new EmaDTO(emaService.findByID(id)));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody EmaDTO objDto){
		
		Ema obj = emaService.fromDTO(objDto);
		obj = emaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id{}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody String id){
		
		emaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody String id, @RequestBody EmaDTO objDto){
		
		Ema obj = emaService.fromDTO(objDto);
		obj.setId(id);
		obj = emaService.update(obj);
		return ResponseEntity.noContent().build();
	}
}