package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 2451143504264217423L;

	public InternalServerErrorException(String msg) {
		super(msg);
	}
	
}
