package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;

public class WrapperException extends RuntimeException {

	private static final long serialVersionUID = -8048736310132173352L;
	
	public WrapperException(HttpStatus status, String body) {
		
		switch (status) {
		case NOT_FOUND:
			throw new NotFoundException(body);
		case INTERNAL_SERVER_ERROR:
			throw new InternalServerErrorException(body);
		case UNAUTHORIZED:
			throw new UnauthorizedException(body);
		case FORBIDDEN:
			throw new ForbiddenException(body);
		case CONFLICT:
			throw new ConflictException(body);
		default:
			throw new RuntimeException("CODE: " + status.value() + " - "  + body);
		}
		
	}

}
