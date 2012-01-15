package com.namespace.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserGAE implements UserDetails{
	
	@Id
	private String username;
	
	private String password;
	
	private boolean admin;
	
	private boolean enabled;
	
	private boolean bannedUser;
	
	private boolean accountNonExpired;
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserGAE(String username, String password, boolean admin, boolean enabled, boolean bannedUser, boolean accountNonExpired) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.enabled = enabled;
		this.bannedUser = bannedUser;
		this.accountNonExpired = accountNonExpired;
	}

	public UserGAE(String username, String password, boolean admin, boolean enabled, boolean bannedUser) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.enabled = enabled;
		this.bannedUser = bannedUser;
	}

	public UserGAE(String username, String password, boolean admin, boolean enabled) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.enabled = enabled;
	}
	
	public UserGAE(String username, String password, boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	
	public UserGAE(String username){
		this.username = username;
	}
	
	public UserGAE() {
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean isBannedUser() {
		return bannedUser;
	}

	public void setBannedUser(boolean bannedUser) {
		this.bannedUser = bannedUser;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		authorityList.add(new GrantedAuthorityImpl("ROLE_USER"));
		if(admin){
			authorityList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authorityList;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
//		return true;
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !bannedUser;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserGAE [username=" + username + ", password=" + password
				+ ", admin=" + admin + ", enabled=" + enabled + ", bannedUser="
				+ bannedUser + ", accountNonExpired=" + accountNonExpired + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accountNonExpired ? 1231 : 1237);
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + (bannedUser ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGAE other = (UserGAE) obj;
		if (accountNonExpired != other.accountNonExpired)
			return false;
		if (admin != other.admin)
			return false;
		if (bannedUser != other.bannedUser)
			return false;
		if (enabled != other.enabled)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}	
	

	

}
