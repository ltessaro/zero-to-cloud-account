package cc.cloudjourney.account.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.cloudjourney.account.models.Transaction;
import cc.cloudjourney.account.models.TransactionDAO;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Transaction> listTransactions() {
		return this.transactionDAO.findAll();
	}
 
	@RequestMapping(method = RequestMethod.GET, value = "/create/{accountID}")
	public Transaction createTransaction(@PathVariable Long accountID) {
		//deposit, withdrawal, transfer, tax
		Transaction trans = new Transaction(accountID, 12.0, "withdrawal", new Date());
		//FIXME: update my balance
		return this.transactionDAO.save(trans);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{accountID}/{amount}")
	public Transaction createTransaction(@PathVariable Long accountID, double amount) {
		//FIXME: get my account from session
		Long myAccountID = 0l;
		if (amount < 1) throw new TransactionAmountException(amount);
		
		Date transactionDate = new Date();
		Transaction transOrigin = new Transaction(myAccountID, amount*-1, "transfer", transactionDate);
		Transaction transDestination = new Transaction(accountID, amount, "transfer", transactionDate);
		this.transactionDAO.save(transOrigin);
		return this.transactionDAO.save(transDestination);
	}

	@Autowired
  	private TransactionDAO transactionDAO;
}