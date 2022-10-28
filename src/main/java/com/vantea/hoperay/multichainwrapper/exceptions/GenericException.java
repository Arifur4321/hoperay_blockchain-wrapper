package com.vantea.hoperay.multichainwrapper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 2451143504264217423L;

	public GenericException(ClientResponse response) {
		super("ERROR STATUS : " + response.rawStatusCode() + " - " + response.bodyToMono(String.class).block());
	}
	
}
