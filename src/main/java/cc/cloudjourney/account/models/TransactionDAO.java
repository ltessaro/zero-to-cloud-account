package cc.cloudjourney.account.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface TransactionDAO extends CrudRepository<Transaction, Long> {
}
