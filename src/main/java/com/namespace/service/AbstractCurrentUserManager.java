package com.namespace.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.namespace.domain.UserGAE;

public abstract class AbstractCurrentUserManager implements CurrentUserManager {
	
	@Override
	public UserGAE getEnabledUser() {
		UserGAE principal = (UserGAE) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal;
	}

}
