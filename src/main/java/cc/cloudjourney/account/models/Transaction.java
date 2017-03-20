package cc.cloudjourney.account.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Account account;
	@NotNull
	private Date transactionAt;
	@NotNull
	private double amount;
	@NotNull
	private String transactionType;

	public Long getId() {
		return id;
	}

	@ManyToOne
    @JoinColumn(name = "account_id")
	public Account getAccount() {
		return account;
	}

	public Date getTransactionAt() {
		return transactionAt;
	}

	public double getAmount() {
		return amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public Transaction(Long accountId, double amount, String transType, Date transAt) {
		this.account = new Account(accountId);
		this.amount = amount;
		this.transactionType = transType;
		this.transactionAt = transAt;
	}

	@Override
	public String toString() {
		return String.format("Transaction[id=%d, account=%s, amount=%s, transactionType='%s', transactionAt='%s']", id,
				account.getId(), amount, transactionType, sdf.format(transactionAt));
	}

	Transaction() {
	}
}
