package cc.cloudjourney.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.cloudjourney.account.models.Account;
import cc.cloudjourney.account.models.AccountDAO;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Account> listAccounts(@PathVariable String userId) {
		this.validateUser(userId);
		return this.accountDao.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public Account getAccount(@PathVariable String userId) {
		this.validateUser(userId);
		return this.accountDao.findByUserId(userId).get();
	}

	private void validateUser(String userId) {
		this.accountDao.findByUserId(userId).orElseThrow(
				() -> new UserAccountNotFoundException(userId));
	}
 
	@RequestMapping(method = RequestMethod.GET, value = "/create")
	public Account createAccount() {
		long salary = 0;
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
		Account acc = new Account("1234567890", accType);
		acc.setBalance(salary/10000);
		return this.accountDao.save(acc);
	}
	
	@Autowired
  	private AccountDAO accountDao;
}