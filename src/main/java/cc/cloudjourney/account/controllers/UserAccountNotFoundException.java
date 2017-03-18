package cc.cloudjourney.account.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserAccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715850382499900092L;

	public UserAccountNotFoundException(Long userId) {
		super("Could not find account for user '" + userId + "'.");
	}
}
