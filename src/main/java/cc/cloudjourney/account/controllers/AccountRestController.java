package cc.cloudjourney.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.cloudjourney.account.models.Account;
import cc.cloudjourney.account.models.AccountDAO;

@RestController
@RequestMapping("/{userId}/accounts")
public class AccountRestController {

	@RequestMapping(method = RequestMethod.GET)
	public Account listAccounts(@PathVariable Long userId) {
		this.validateUser(userId);
		return this.accountDao.findByUserId(userId).get();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{accountID}")
	public Account getAccount(@PathVariable Long userId, @PathVariable Long accountID) {
		this.validateUser(userId);
		return this.accountDao.findOne(accountID);
	}

	private void validateUser(Long userId) {
		this.accountDao.findByUserId(userId).orElseThrow(
				() -> new UserAccountNotFoundException(userId));
	}
  
	@Autowired
  	private AccountDAO accountDao;
}