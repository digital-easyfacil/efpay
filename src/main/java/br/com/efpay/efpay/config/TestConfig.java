package br.com.efpay.efpay.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.efpay.efpay.dto.CepDTO;
import br.com.efpay.efpay.entities.AddressEntity;
import br.com.efpay.efpay.entities.CepEntity;
import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.services.AddressessService;
import br.com.efpay.efpay.services.CepService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private CepService service;
	
	@Autowired
	private AddressessService addressessService;	
	
	private static final Logger logger = LogManager.getLogger(TestConfig.class);
	
	CepEntity cepEntity;
	
	@Override
	public void run(String... args) {
		/*
		try {
			
			cepEntity = service.consultarCep("08460500");
			
			logger.info("CEP from consultarCep " + cepEntity.getCep());
		} catch (Exception e) {
			CepDTO cepDTO;
			try {
				cepDTO = service.consultarCepByViacep("08460500");
				
				cepEntity = new CepEntity();
				
				cepEntity.setCep(cepDTO.getCep());
				cepEntity.setLogradouro(cepDTO.getLogradouro());
				cepEntity.setComplemento(cepDTO.getComplemento());
				cepEntity.setBairro(cepDTO.getBairro());
				cepEntity.setLocalidade(cepDTO.getLocalidade());
				cepEntity.setUf(cepDTO.getUf());
				cepEntity.setUnidade(cepDTO.getUnidade());
				cepEntity.setIbge(cepDTO.getIbge());
				cepEntity.setGia(cepDTO.getGia());
				
				//service.createCep(cepEntity);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			AddressEntity address = new AddressEntity();
			
			address.setNumber("12A");
			address.setZipCode(cepEntity);
			
			UserEntity userEntity = new UserEntity();
			userEntity.setId(1L);
			
			address.setUser(userEntity);
			address.setComplement("");
			
			try {
				//addressessService.createAddress(address);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}*/
	}
}
