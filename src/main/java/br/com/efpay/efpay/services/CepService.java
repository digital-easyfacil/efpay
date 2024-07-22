package br.com.efpay.efpay.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.efpay.efpay.dto.CepDTO;
import br.com.efpay.efpay.entities.CepEntity;
import br.com.efpay.efpay.exceptions.CepNotFoundException;
import br.com.efpay.efpay.repositories.CepRepository;

@Service
public class CepService {
	@Autowired
	private CepRepository cepRepository;
	private static final Logger logger = LogManager.getLogger(UsersService.class);
	
	private static final String URL_TEMPLATE = "https://viacep.com.br/ws/%s/json/";

    public CepDTO consultarCepByViacep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_TEMPLATE, cep);

        try {
            ResponseEntity<CepDTO> response = restTemplate.getForEntity(url, CepDTO.class);
            if (response.getStatusCode() != HttpStatus.OK || response.getBody().getCep() == null) {
            	logger.error("CEP não encontrado: " + cep);
                throw new CepNotFoundException("CEP não encontrado: " + cep);
            }
            
            System.out.println(response.getBody().getCep());
            
            return response.getBody();
        } catch (HttpClientErrorException e) {
        	logger.error(e.getMessage());
        	throw new CepNotFoundException("CEP não encontrado: " + cep);
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	throw new CepNotFoundException("CEP não encontrado: " + cep);
        }
    }    

	public CepEntity consultarCep(String cep) {
		CepEntity cepEntity = cepRepository.findById(cep)
				.orElseThrow(() -> new CepNotFoundException("CEP " + cep + " não encontrado"));
		
		return cepEntity;
	}	
	
	public void createCep(CepEntity cepEntity) {
		cepRepository.save(cepEntity);
	}
}
