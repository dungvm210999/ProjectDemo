package dung.vm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dung.vm.demo.dto.AccountForm;
import dung.vm.demo.entity.Account;
import dung.vm.demo.service.AccountService;
//import dung.vm.demo.service.AccountService;
import dung.vm.demo.service.RoleService;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class AccountController {
	
//	@Autowired
//	AccountService accountService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	RoleService roleService;
	
//	@PostMapping(value = { "/create-account" })
//	public Account saveAccount(@RequestBody AccountForm accountForm) {
//		return accountService.createAccount(accountForm);
//	}

}
