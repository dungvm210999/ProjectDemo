package dung.vm.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

@Entity
@SuppressWarnings("serial")
@NoArgsConstructor
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@NotNull(message = "Bạn chưa điền tên đăng nhập!")
	@Column(name = "username", length = 36)
	private String username;

	@NotNull(message = "Bạn chưa nhập mật khẩu!")
	@Column(name = "encryted_password", length = 128, nullable = false)
	private String encryptPassword;

	@NotNull(message = "Vui lòng nhập tên!")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@NotNull(message = "Vui lòng nhập họ!")
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private Integer gender;

	@NotNull(message = "Vui lòng nhập email!")
	@Column(name = "email")
	private String email;

	@Column(name = "number_phone", length = 10)
	private String phoneNumber;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_at")
	private Date updateAt;

	@Column(name = "is_delete", columnDefinition = "Boolean default false")
	private boolean isDelete;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Account() {
		super();
	}

	public Account(String username, String encryptPassword, String firstName, String middleName, String lastName,
			Integer gender, String email, String phoneNumber, String description, String address) {
		this.username = username;
		this.encryptPassword = encryptPassword;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.address = address;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberPhone() {
		return phoneNumber;
	}

	public void setNumberPhone(String numberPhone) {
		this.phoneNumber = numberPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
