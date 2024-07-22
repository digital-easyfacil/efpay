package br.com.efpay.efpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.entities.UserTokenEntity;

@Service
public class EmailService {
	@Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UsersTokenService tokenService;

    public void sendValidationTokenByEmail(UserEntity user) {
        UserTokenEntity user_token = tokenService.generateToken(user);
        String subject = "Email Validation Token";
        String text = "Please use the following token to validate your email: " + user_token.getToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("token@efpay.com.br");
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
