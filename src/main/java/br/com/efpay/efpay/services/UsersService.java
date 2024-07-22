package br.com.efpay.efpay.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.entities.AddressEntity;
import br.com.efpay.efpay.entities.PhoneNumberEntity;
import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.entities.UserStatusEntity;
import br.com.efpay.efpay.exceptions.UserNotFoundException;
import br.com.efpay.efpay.repositories.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class UsersService {
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	private static final Logger logger = LogManager.getLogger(UsersService.class);

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID " + id + " do usário não encontrado."));
    }
	
	public UserEntity findByCPF(String cpf) {
        return userRepository.findUserByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("CPF: " + cpf + " não encontrado."));
    }	
	
	@Transactional
    public void saveUserWithAssociations(UserEntity user, List<PhoneNumberEntity> phones, List<AddressEntity> addresses) {
        for (PhoneNumberEntity phone : phones) {
            phone.setUser(user);
        }
        for (AddressEntity address : addresses) {
            address.setUser(user);
        }
        
        user.setPhones(phones);
        user.setAddress(addresses);
        
        userRepository.save(user);
    }
	
	@Transactional
    public void createUser(UserEntity user){
		Optional<UserEntity> userEntity;
				
		if (user.getCpf() != null && user.getCpf().trim().length() > 0) {
			userEntity = userRepository.findUserByCpf(user.getCpf());
			
			if (!userEntity.isEmpty()) {
				throw new UserNotFoundException("CPF " + user.getCpf() + " já cadastrado.");
			}			
		}

		if (user.getCnpj() != null && user.getCnpj().trim().length() > 0) {
			userEntity = userRepository.findUserByCnpj(user.getCnpj());
			
			if (!userEntity.isEmpty()) {
				throw new UserNotFoundException("CNPJ " + user.getCnpj() + " já cadastrado.");
			}			
		}
		
		if (user.getEmail() != null && user.getEmail().length() > 0) {
			userEntity = userRepository.findUserByEmail(user.getEmail());
			
			if (!userEntity.isEmpty()) {
				throw new UserNotFoundException("E-mail " + user.getEmail() + " já cadastrado.");
			}			
		}		
		
		UserStatusEntity userStatus = new UserStatusEntity();
		userStatus.setId(3);

		user.setUserStatus(userStatus);
		
		userRepository.save(user);
		
		Optional<UserEntity> userEntitySaved = userRepository.findUserByEmail(user.getEmail());
		
		if (userEntitySaved.isPresent()) {
			emailService.sendValidationTokenByEmail(userEntitySaved.get());
		}
    }	
}
