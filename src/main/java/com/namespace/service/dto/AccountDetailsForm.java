package com.namespace.service.dto;

/**
 * This class transfer the data from the view  
 * to the form controller 
 * @author David
 */
public class AccountDetailsForm {

    private String firstName;

    private String lastName;

    private String email;
 
    private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountDetailsForm(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
    
	public AccountDetailsForm() {
	}

	@Override
	public String toString() {
		return "AccountDetailsForm [firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password
				+ "]";
	}

	
}
