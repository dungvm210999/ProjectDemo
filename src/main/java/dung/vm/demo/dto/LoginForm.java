package dung.vm.demo.dto;

public class LoginForm {
	
	//@NotNull
	private String username;
	
	//@NotNull
	private String encryptPassword;

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
	
	

}
