package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 527813437852768002L;
	
	public ConflictException(String msg) {
		super(msg);
	}

}
