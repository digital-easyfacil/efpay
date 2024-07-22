package br.com.efpay.efpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.efpay.efpay.services.CepService;

@RestController
@RequestMapping("/api")
public class CepController {
	@Autowired
    private CepService cepService;

}
