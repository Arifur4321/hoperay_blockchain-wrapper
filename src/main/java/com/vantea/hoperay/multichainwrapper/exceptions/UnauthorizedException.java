package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2451143504264217423L;

	public UnauthorizedException(String msg) {
		super(msg);
	}
	
}
