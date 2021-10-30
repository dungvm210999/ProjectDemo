package dung.vm.demo.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dung.vm.demo.common.ERole;
import dung.vm.demo.common.JwtUtils;
import dung.vm.demo.dto.LoginForm;
import dung.vm.demo.dto.RegisterForm;
import dung.vm.demo.entity.Account;
import dung.vm.demo.entity.Role;
import dung.vm.demo.repository.AccountRepository;
import dung.vm.demo.repository.RoleRepository;
import dung.vm.demo.response.JwtResponse;
import dung.vm.demo.service.imp.UserDetailsImp;

@CrossOrigin("*")
@RequestMapping("/api/v1")
@RestController
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginForm) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getEncryptPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getAccountId(), userDetails.getFirstName(),
				userDetails.getMiddleName(), userDetails.getLastName(), userDetails.getUsername(),
				userDetails.getGender(), userDetails.getPhoneNumber(), userDetails.getEmail(), roles));
	}

	@PostMapping("/register")
	public Map<String, Object> registerUser(@Valid @RequestBody RegisterForm registerForm, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (err.hasErrors()) {
				System.out.println("---> auth controller 82");
				for (ObjectError element : err.getAllErrors()) {
					map.put("er", element.getDefaultMessage());
					return map;
				}
			} else {
				System.out.println("---> auth controller 88");
				if (accountRepository.existsByUsername(registerForm.getUsername())) {
					System.out.println("---> auth controller 90");
					map.put("statusRegister", false);
					return map;
				} else {
					// Create new user's account
					Account user = new Account(registerForm.getUsername(),
							encoder.encode(registerForm.getEncryptPassword()), registerForm.getFirstName(),
							registerForm.getMiddleName(), registerForm.getLastName(), registerForm.getGender(),
							registerForm.getEmail(), registerForm.getPhoneNumber(), registerForm.getDescription(),
							registerForm.getAddress());
					System.out.println("---> " + user.getUsername());
//					Account user = new Account();
//					user.setUsername(registerForm.getUsername());
//					user.setFirstName(registerForm.getFirstName());
//					user.setMiddleName(registerForm.getMiddleName());
//					user.setLastName(registerForm.getLastName());
//					user.setGender(registerForm.getGender());
//					user.setPhoneNumber(registerForm.getPhoneNumber());
//					user.setEncryptPassword(registerForm.getEncryptPassword());
					Set<String> strRoles = registerForm.getRole();
					Set<Role> roles = new HashSet<>();
					if (strRoles == null) {
						System.out.println("---> auth controller 109");
						Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						System.out.println("---> 1: " + userRole);
						roles.add(userRole);
						System.out.println("---> 2: " + userRole);
					} else {
						System.out.println("---> auth controller 114");
						strRoles.forEach(role -> {
							switch (role) {
							case "admin":
								Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(adminRole);

								break;
							default:
								Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(userRole);
							}
						});
					}
					System.out.println("---> auth controller 130");
					user.setRoles(roles);
					accountRepository.save(user);
					map.put("statusRegister", true);
					return map;
				}
			}
		} catch (Exception e) {
			System.out.println("---> auth controller 138");
			map.put("statusRegister", 500);
		}
		return map;
	}
}
