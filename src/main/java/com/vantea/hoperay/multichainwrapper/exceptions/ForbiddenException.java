package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 5406842322910565539L;
	
	public ForbiddenException(String msg) {
		super(msg);	
	}

}
