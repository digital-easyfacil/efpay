package br.com.efpay.efpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.entities.PhoneNumberEntity;
import br.com.efpay.efpay.exceptions.PhoneNumberNotFoundException;
import br.com.efpay.efpay.repositories.PhoneNumbersRepository;

@Service
public class PhoneNumbersService {
	@Autowired
	private PhoneNumbersRepository pnr;
	
	public PhoneNumberEntity searchByPhoneNumber(String phoneNumber) throws Exception{
		return pnr.findPhoneNumberByNumber(phoneNumber)
				.orElseThrow(() -> new PhoneNumberNotFoundException("Número de telefone " + phoneNumber + " não encontrado"));
	}	
}
