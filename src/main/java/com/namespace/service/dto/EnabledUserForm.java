package com.namespace.service.dto;

import java.util.List;

public class EnabledUserForm {

	private List<String> deactivate;

	public List<String> getDeactivate() {
		return deactivate;
	}

	public void setDeactivate(List<String> deactivate) {
		this.deactivate = deactivate;
	}

	@Override
	public String toString() {
		return "EnabledUserForm [deactivate=" + deactivate + "]";
	}


	
}
