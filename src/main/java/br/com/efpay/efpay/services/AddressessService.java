package br.com.efpay.efpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.dto.CepDTO;
import br.com.efpay.efpay.entities.AddressEntity;
import br.com.efpay.efpay.entities.CepEntity;
import br.com.efpay.efpay.exceptions.AddressNotFoundException;
import br.com.efpay.efpay.exceptions.CepNotFoundException;
import br.com.efpay.efpay.repositories.AddressesRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressessService {
	@Autowired
	private AddressesRepository addressesRepository;

	@Autowired
	private CepService cepService;

	public AddressEntity findAddressByCep(String cep) {
		CepEntity cepEntity = new CepEntity();
		cepEntity.setCep(cep);

		return addressesRepository.findAddressByCep(cepEntity)
				.orElseThrow(() -> new AddressNotFoundException("CEP " + cepEntity.getCep() + " não encontrado"));
	}

	@Transactional
	public void createAddress(AddressEntity address) {
		try {
			AddressEntity addressEntity = findAddressByCep(address.getZipCode().getCep());

			address.setZipCode(addressEntity.getZipCode());

			addressesRepository.save(address);
		} catch (AddressNotFoundException a) {
			CepEntity cepEntity = new CepEntity();

			try {
				cepEntity = cepService.consultarCep(address.getZipCode().getCep());

				address.setZipCode(cepEntity);

				addressesRepository.save(address);
			} catch (CepNotFoundException cepException) {
				try {
					CepDTO cepDTO = cepService.consultarCepByViacep(address.getZipCode().getCep());

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

					cepService.createCep(cepEntity);

					address.setZipCode(cepEntity);

					addressesRepository.save(address);
				} catch (CepNotFoundException cep) {
					System.out.println(cep);
					throw cep;
				}
			}
		}
	}
}
