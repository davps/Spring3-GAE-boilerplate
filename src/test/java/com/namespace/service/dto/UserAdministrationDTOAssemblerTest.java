package com.namespace.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.namespace.domain.Account;
import com.namespace.domain.UserGAE;
import com.namespace.service.dto.UserAdministrationForm;
import com.namespace.service.dto.UserAdministrationFormAssembler;

public class UserAdministrationDTOAssemblerTest {

	private UserAdministrationFormAssembler assembler;
	
	@Before
	public void setUp(){
		this.assembler = new UserAdministrationFormAssembler();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void createUserAdministrationDTO(){
		UserAdministrationForm formNull = this.assembler.createUserAdministrationForm(null, null);
		assertUserNull(formNull);
		assertAccountNull(formNull);
		
		UserGAE user = new UserGAE("user", "12345", true);
		Account account = new Account(new Long(1), "David", "D.", "example@example.com", null);

		UserAdministrationForm form1 = this.assembler.createUserAdministrationForm(null, account);
		assertUserNull(form1);
		assertAccountEquals(form1, account);

		UserAdministrationForm form2 = this.assembler.createUserAdministrationForm(user, null);
		assertUserEquals(form2, user);
		assertAccountNull(form2);

		UserAdministrationForm form3 = this.assembler.createUserAdministrationForm(user, account);
		assertUserEquals(form3, user);
		assertAccountEquals(form3, account);
		
		UserAdministrationForm form = this.assembler.createUserAdministrationForm(user, account);
		assertUserEquals(form, user);
		assertAccountEquals(form, account);
		
		Map<String, Object> map = this.assembler.copyNewUserFromUserAdministrationForm(form);
		
		UserGAE userFromForm = (UserGAE) map.get("user");
		Account accountFromForm = (Account) map.get("account");
		
		//TODO: Fix theses tests
//		assertEquals(user, userFromForm);
//		assertEquals(account, accountFromForm);
//		assertEquals(scheduler, schedulerFromForm);
	}
	
	
	private void assertUserNull(UserAdministrationForm form){
		assertNull(form.getUsername());
		assertFalse(form.isAdmin());
		assertFalse(form.isBannedUser());
		assertFalse(form.isEnabled());
		assertNull(form.getPassword());
		
	}
	
	private void assertUserEquals(UserAdministrationForm form, UserGAE user){
		assertEquals(user.getUsername() , form.getUsername());
		assertEquals(user.isAdmin() , form.isAdmin());
		assertEquals(user.isBannedUser() , form.isBannedUser());
		assertEquals(user.isEnabled() , form.isEnabled());
		assertNull(form.getPassword());
	}
	
	private void assertAccountNull(UserAdministrationForm form){
		assertNull(form.getEmail());
		assertNull(form.getFirstName());
		assertNull(form.getLastName());
	}
	
	
	private void assertAccountEquals(UserAdministrationForm form, Account account){
		assertEquals(account.getEmail() , form.getEmail());
		assertEquals(account.getFirstName() , form.getFirstName());
		assertEquals(account.getLastName() , form.getLastName());
	}
	
}
