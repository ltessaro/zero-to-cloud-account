package cc.cloudjourney.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.cloudjourney.account.models.Account;
import cc.cloudjourney.account.models.AccountDAO;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Account>> listAccounts(@RequestHeader(value="X-Z2c-Token", defaultValue="") String token) {
		try {
			this.validateToken(token);
			return new ResponseEntity<Iterable<Account>>(this.accountDao.findAll(), HttpStatus.OK);
		} catch (TokenNotFoundException ex) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public ResponseEntity<Account> getAccount(@RequestHeader(value="X-Z2c-Token", defaultValue="") String token, @PathVariable String userId) {
		try {
			this.validateToken(token);
			this.validateUser(userId);
			return new ResponseEntity<Account>(this.accountDao.findByUserId(userId).get(), HttpStatus.OK);
		} catch (UserAccountNotFoundException ex) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		} catch (TokenNotFoundException ex) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create/{userId}")
	public ResponseEntity<Account> createAccount(@PathVariable String userId, @RequestParam("salary") long salary) {
		String accType = "student";
		if (salary > 500000) {
			accType = "private";
		} else if (salary > 200000) {
			accType = "premium";
		} else if (salary > 100000) {
			accType = "gold";
		} else if (salary > 20000) {
			accType = "standard";
		}
		Account acc = new Account(userId, accType);
		acc.setBalance(salary/10000);
		//FIXME: update balance
		return new ResponseEntity<Account>(this.accountDao.save(acc), HttpStatus.OK);
	}
	

	
	private void validateUser(String userId) {
		this.accountDao.findByUserId(userId).orElseThrow(
				() -> new UserAccountNotFoundException(userId));
	}
 
	@Autowired
  	private AccountDAO accountDao;
	
	private void validateToken(String token) {
		//must search on DynamoDB session table.
	}
}