package cc.cloudjourney.account.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "transaction.account", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double setBalance(double balance) {
        return this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }
    
    public String getUserId() {
    	return userId;
    }

    public List<Transaction> getTransactions() {
    	return this.transactions;
    }

    @NotNull
    private String userId;
	@NotNull
    private double balance;
	@NotNull
    private String accountType;

    public Account(String userId, String accountType) {
        this.userId = userId;
        this.accountType = accountType;
    }

    public Account(Long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, userId=%s, balance=%s, type='%s']",
                id, userId, balance, accountType);
    }
    
    Account() {
    } 
}
