package dung.vm.demo.service.imp;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dung.vm.demo.entity.Account;
import dung.vm.demo.repository.AccountRepository;
import dung.vm.demo.service.AccountService;

@Service
public class UserDetailsServiceImp implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	@Override
	public Account save(Account account) {
		account.setEncryptPassword(passwordEncoder.encode(account.getEncryptPassword()));
		return accountRepository.save(account);
	}

	@Override
	public void remove(Long accountId) {
		accountRepository.deleteById(accountId);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> userOptional = accountRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserDetailsImp.build(userOptional.get());
	}

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

}
