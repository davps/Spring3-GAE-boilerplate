package com.namespace.service.dto;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;

public class AccountControllerForm {

    private String firstName;

    private String lastName;

    private String email;

	private String oldPassword;
	
	private String newPassword;
	
	private String newPasswordConfirmation;

	private boolean accountNonExpired;

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
    
    public AccountControllerForm() {
		// TODO Auto-generated constructor stub
	}

    public AccountControllerForm(Account account,  UserGAE userGAE) {
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.email = account.getEmail();

		/*The view never see the password*/
		//this.password = null;
		this.accountNonExpired = userGAE.isAccountNonExpired();
	}

    
    
}
