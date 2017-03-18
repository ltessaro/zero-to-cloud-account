package cc.cloudjourney.account.models;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface AccountDAO extends CrudRepository<Account, Long> {
  public Optional<Account> findByUserId(Long userId);
}
