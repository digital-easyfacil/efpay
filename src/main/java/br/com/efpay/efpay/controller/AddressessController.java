package br.com.efpay.efpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.efpay.efpay.entities.AddressEntity;
import br.com.efpay.efpay.services.AddressessService;

@RestController
@RequestMapping("/api/addressess")
public class AddressessController {
	
	@Autowired
	private AddressessService service;
	
	@GetMapping(value = "/{cep}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressEntity> findAddressByCep(@PathVariable String cep){
		AddressEntity address = service.findAddressByCep(cep);
		
		return ResponseEntity.ok().body(address);
	}
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressEntity> createAddress(@RequestBody AddressEntity address) {
		
		service.createAddress(address);
		
		return ResponseEntity.ok().body(address);
	}		
	
}
