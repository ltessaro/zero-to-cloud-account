package cc.cloudjourney.account.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class TokenNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4777681207637835201L;

	public TokenNotFoundException(String token) {
		super(String.format("Token not found %s'.", token));
	}
}
