package cc.cloudjourney.account.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.cloudjourney.account.models.Transaction;
import cc.cloudjourney.account.models.TransactionDAO;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Transaction>> listTransactions(@RequestHeader(value="X-Z2c-Token", defaultValue="") String token) {
		try {
			this.validateToken(token);
			return new ResponseEntity<Iterable<Transaction>>(this.transactionDAO.findAll(), HttpStatus.OK);
		} catch (TokenNotFoundException ex) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
 
	@RequestMapping(method = RequestMethod.POST, value = "/create/{accountID}")
	public ResponseEntity<Transaction> createTransaction(@RequestHeader(value="X-Z2c-Token", defaultValue="") String token, @PathVariable Long accountID) {
		try {
			this.validateToken(token);
			//deposit, withdrawal, transfer, tax
			Transaction trans = new Transaction(accountID, 12.0, "withdrawal", new Date());
			//FIXME: update my balance
			this.transactionDAO.save(trans);
			return new ResponseEntity<Transaction>(trans, HttpStatus.OK);
		} catch (TokenNotFoundException ex) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transfer/{accountID}/{amount}")
	public ResponseEntity<Transaction> createTransaction(@RequestHeader(value="X-Z2c-Token", defaultValue="") String token, @PathVariable Long accountID, double amount) {
		try {
			this.validateToken(token);
			//FIXME: get my account from session
			Long myAccountID = 0l;
			if (amount < 1) throw new TransactionAmountException(amount);

			Date transactionDate = new Date();
			Transaction transOrigin = new Transaction(myAccountID, amount*-1, "transfer", transactionDate);
			Transaction transDestination = new Transaction(accountID, amount, "transfer", transactionDate);
			this.transactionDAO.save(transOrigin);
			this.transactionDAO.save(transDestination);
			return new ResponseEntity<Transaction>(transDestination, HttpStatus.OK);
		} catch (TokenNotFoundException ex) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	@Autowired
  	private TransactionDAO transactionDAO;
	
	private void validateToken(String token) {
		//must search on DynamoDB session table.
	}
}