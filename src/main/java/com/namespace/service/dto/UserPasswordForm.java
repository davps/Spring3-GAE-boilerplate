package com.namespace.service.dto;

public class UserPasswordForm {

	private String oldPassword;
	
	private String newPassword;
	
	private String newPasswordConfirmation;
	
	private boolean accountNonExpired;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public UserPasswordForm() {
	}

	@Override
	public String toString() {
		return "UserPasswordForm [oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", newPasswordConfirmation="
				+ newPasswordConfirmation + ", accountNonExpired="
				+ accountNonExpired + "]";
	}
	
	
	
}
