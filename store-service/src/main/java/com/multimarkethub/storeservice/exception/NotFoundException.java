package com.multimarkethub.storeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	

	private static final long serialVersionUID = 446600076320737290L;

	public NotFoundException(String message) {
		super(message);
	}

}
