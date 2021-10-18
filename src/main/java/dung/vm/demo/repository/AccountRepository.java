package dung.vm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dung.vm.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

}
