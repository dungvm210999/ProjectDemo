package dung.vm.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import dung.vm.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

	@Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.accountId = :accountId")
	Optional<Account> findByAccountId(Long accountId);

	Optional<Account> findByUsername(String username);

	Boolean existsByUsername(String username);

}
