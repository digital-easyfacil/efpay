package br.com.efpay.efpay.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsDTO {
	private String message;
    private String sms_to;
    private String senderId;
    private String callbackUrl;
    private String user_id;
    
    public SmsDTO() {
    	
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSms_to() {
		return sms_to;
	}

	public void setSms_to(String sms_to) {
		this.sms_to = sms_to;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(callbackUrl, message, senderId, sms_to, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmsDTO other = (SmsDTO) obj;
		return Objects.equals(callbackUrl, other.callbackUrl) && Objects.equals(message, other.message)
				&& Objects.equals(senderId, other.senderId) && Objects.equals(sms_to, other.sms_to)
				&& Objects.equals(user_id, other.user_id);
	}  
	
	
}
