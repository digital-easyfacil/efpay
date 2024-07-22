package br.com.efpay.efpay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.efpay.efpay.dto.ApiResponseDTO;
import br.com.efpay.efpay.dto.SmsDTO;
import br.com.efpay.efpay.entities.SmsLogEntity;
import br.com.efpay.efpay.entities.UserEntity;
import br.com.efpay.efpay.services.SmsLogService;
import br.com.efpay.efpay.util.SmsUtil;

@RestController
@RequestMapping("/api/sms")
public class SmsSmsLogController {
	@Autowired
    private SmsLogService service;
	
	@Autowired
    private SmsUtil smsUtil;	
	
	@PostMapping("/send")
    public ResponseEntity<ApiResponseDTO> sendSms(@RequestBody SmsDTO smsRequest) {
        try {
        	UserEntity user = new UserEntity();
        	user.setId(Long.parseLong(smsRequest.getUser_id()));
        	
            SmsLogEntity smsLog = new SmsLogEntity();
            smsLog.setMessage(smsRequest.getMessage());
            smsLog.setSmsTo(smsRequest.getSms_to());
            smsLog.setSenderId(smsRequest.getSenderId());
            smsLog.setCallbackUrl(smsRequest.getCallbackUrl());
            smsLog.setUser(user);

            smsUtil.sendSms(smsRequest.getMessage(), smsRequest.getSms_to(), smsRequest.getSenderId(), smsRequest.getCallbackUrl());
            
            service.createSmsLog(smsLog);
            
            ApiResponseDTO response = new ApiResponseDTO("success", "SMS sent successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);            

        } catch (IOException e) {
            e.printStackTrace();
            ApiResponseDTO response = new ApiResponseDTO("error", "SMS sent successfully!");
            
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
