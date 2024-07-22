package br.com.efpay.efpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.services.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> findAll() {
		List<UserEntity> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) throws Exception {
		UserEntity user = service.findById(id);
		
		if (id == null) {
			throw new Exception();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		
		service.createUser(user);
		
		return ResponseEntity.ok().body(user);
	}	
}
