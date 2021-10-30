package dung.vm.demo.service.imp;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dung.vm.demo.entity.Account;

public class UserDetailsImp implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String username;
	private String email;
	private int gender;
	private String phoneNumber;
	@JsonIgnore
	private String encryptPassword;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImp(Long accountId, String firstName, String middleName, String lastName, String username,
			String email, int gender, String phoneNumber, String encryptPassword,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.accountId = accountId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.encryptPassword = encryptPassword;
		this.authorities = authorities;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static UserDetails build(Account account) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
		return new UserDetailsImp(account.getAccountId(), account.getFirstName(), account.getMiddleName(),
				account.getLastName(), account.getUsername(), account.getEmail(),
				account.getGender(), account.getNumberPhone(), account.getEncryptPassword(), authorities);
	}

	@Override
	public String getPassword() {
		return encryptPassword;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImp account = (UserDetailsImp) o;
		return Objects.equals(accountId, account.accountId);
	}

}
