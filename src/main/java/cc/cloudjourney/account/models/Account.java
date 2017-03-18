package cc.cloudjourney.account.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public String getAccountStatus() {
        return accountStatus;
    }
    
    public Long getUserId() {
    	return userId;
    }

    private Long userId;
    
    private double balance;
    
    private String accountStatus;
    
    private String accountType;

    public Account(Long userId, String accountStatus, String accountType) {
        this.userId = userId;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, userId=%s, balance=%s, status='%s', type='%s']",
                id, userId, balance, accountStatus, accountType);
    }
    
    Account() {
    } 
}
