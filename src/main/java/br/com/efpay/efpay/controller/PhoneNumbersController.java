package br.com.efpay.efpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.efpay.efpay.services.PhoneNumbersService;

@RestController
@RequestMapping("/api/phone_numbers")
public class PhoneNumbersController {
	
	@Autowired
	private PhoneNumbersService service;
	
}
