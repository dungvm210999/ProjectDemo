package dung.vm.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import dung.vm.demo.entity.Account;
import dung.vm.demo.service.imp.AccountServiceImp;

@Service
public interface AccountService extends AccountServiceImp<Account>, UserDetailsService {
//	Optional<Account> findByEmail(String email);
	Optional<Account> findByUsername(String username);
}
