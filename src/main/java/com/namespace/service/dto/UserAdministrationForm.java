package com.namespace.service.dto;

public class UserAdministrationForm {

	private String username;
	
    private String firstName;

    private String lastName;

    private String email;
	
	private boolean admin;
	
	private String password;

	private String retypePassword;
	
	private boolean enabled;

	private boolean bannedUser;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isBannedUser() {
		return bannedUser;
	}

	public void setBannedUser(boolean bannedUser) {
		this.bannedUser = bannedUser;
	}

	
	
	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public UserAdministrationForm(String username, String firstName,
			String lastName, String email, boolean admin,
			String password, boolean enabled, boolean bannedUser) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.admin = admin;
		this.password = password;
		this.enabled = enabled;
		this.bannedUser = bannedUser;
	}
	
	public UserAdministrationForm() {
	}

	@Override
	public String toString() {
		return "UserAdministrationForm [username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", admin=" + admin + ", password=" + password
				+ ", retypePassword=" + retypePassword + ", enabled=" + enabled
				+ ", bannedUser=" + bannedUser + "]";
	}

}
