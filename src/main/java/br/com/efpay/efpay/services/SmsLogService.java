package br.com.efpay.efpay.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efpay.efpay.entities.SmsLogEntity;
import br.com.efpay.efpay.repositories.SmsLogRepository;

@Service
public class SmsLogService {
	@Autowired
	private SmsLogRepository smsLogRepository;
	
	private static final Logger logger = LogManager.getLogger(UsersService.class);

    public SmsLogEntity createSmsLog(SmsLogEntity smsLog) {
        return smsLogRepository.save(smsLog);
    }
}
