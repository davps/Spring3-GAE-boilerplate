package com.namespace.service.dto;

import org.springframework.stereotype.Component;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;

@Component
public class AccountFormAssembler {
	
	public AccountControllerForm createAccountControllerForm (Account account, UserGAE userGAE){
		return new AccountControllerForm(account, userGAE);
	}
	
	/**
	 * Account details
	 */
	public AccountDetailsForm createAccountDetailsForm(Account account){
		AccountDetailsForm form = new AccountDetailsForm();
		form.setFirstName(account.getFirstName());
		form.setLastName(account.getLastName());
		form.setEmail(account.getEmail());
		return form; 
	}
	
	public Account copyAccountDetailsFormtoAccount(AccountDetailsForm accountDetailsForm, Account account){
		account.setFirstName(accountDetailsForm.getFirstName());
		account.setLastName(accountDetailsForm.getLastName());
		account.setEmail(accountDetailsForm.getEmail());
		
		return account;
	}

    public UserPasswordForm createUserPasswordForm(UserGAE user){
		return new UserPasswordForm();
    }
    
	public UserGAE copyUserPasswordFormToUserGAE(UserPasswordForm userPasswordForm, UserGAE usergae){
		usergae.setPassword(userPasswordForm.getNewPassword());
		return usergae;
	}
	

}
