package com.namespace.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.namespace.domain.UserGAE;

public class SecurityUtil{

	public static void authenticateUser(UserGAE user){
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword()));
	}
	
}

