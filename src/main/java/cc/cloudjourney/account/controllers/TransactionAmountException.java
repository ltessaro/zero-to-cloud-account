package cc.cloudjourney.account.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class TransactionAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715850382499900092L;

	public TransactionAmountException(Double amount) {
		super(String.format("Amount is invalid for this transaction: %d.", amount));
	}
}
