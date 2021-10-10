package dung.vm.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dung.vm.demo.common.Common;
import dung.vm.demo.dto.AccountForm;
import dung.vm.demo.model.Account;
import dung.vm.demo.model.Role;
import dung.vm.demo.repository.AccountRepository;
import dung.vm.demo.repository.RoleRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public void createAccount(AccountForm accountForm) {
		Account account = new Account();
		account.setUsername(accountForm.getUserName());
        account.setFirstName(accountForm.getFirstName());
        account.setMiddleName(accountForm.getMiddleName());
        account.setLastName(accountForm.getLastName());
        account.setBirthday(Common.stringToDate(accountForm.getBirthDay()));
        account.setEmail(accountForm.getEmail());
        account.setNumberPhone(accountForm.getNumberPhone());
        account.setGender(accountForm.getGender());
        Role role = roleRepository.getOne(accountForm.getRoleId());
        account.setRole(role);
        account.setCreateBy(Common.getUsernameLogin());
        account.setCreateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        accountRepository.save(account);
	}
}
