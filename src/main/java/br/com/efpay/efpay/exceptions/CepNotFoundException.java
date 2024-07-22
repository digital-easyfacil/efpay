package br.com.efpay.efpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CepNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CepNotFoundException(String message) {
        super(message);
    }

    public CepNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
