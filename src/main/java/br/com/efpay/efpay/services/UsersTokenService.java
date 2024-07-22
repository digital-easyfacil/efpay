package br.com.efpay.efpay.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.entities.UserTokenEntity;
import br.com.efpay.efpay.repositories.UsersTokenRepository;

@Service
public class UsersTokenService {
	@Autowired
	private UsersTokenRepository ropository;

	private static final int TOKEN_EXPIRATION_MINUTES = 30; // Tempo de expiração em minutos

	public UserTokenEntity generateToken(UserEntity user) {
		UserTokenEntity token = new UserTokenEntity();

		//token.setToken(UUID.randomUUID().toString());
		token.setToken(generateFourDigitToken());
		token.setCreatedDate(LocalDateTime.now());
		token.setExpiryDate(LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_MINUTES));
		token.setStatus("1");
		token.setUser(user);

		return ropository.save(token);
	}

	public boolean isTokenExpired(UserTokenEntity token) {
		return LocalDateTime.now().isAfter(token.getExpiryDate());
	}

	public boolean validateToken(String tkn) {
		return ropository.findToken(tkn).map(token -> !isTokenExpired(token)).orElse(false);
	}

	private String generateFourDigitToken() {
		SecureRandom random = new SecureRandom();
		int token = random.nextInt(9000) + 1000; // Gera um número aleatório entre 1000 e 9999
		return String.valueOf(token);
	}
}
