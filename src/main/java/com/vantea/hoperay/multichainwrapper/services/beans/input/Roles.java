/**
 * Enumeration mapping roles to MultiChain permissions 
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

public enum Roles {

	COMPANY("COMPANY", "low3"),
	EMPLOYEE("EMPLOYEE", "low2"),
	MERCHANT("MERCHANT", "low1");

	private final String mcRole;

	private final String role;

	Roles(String role, String mcRole) {
		this.role = role;
		this.mcRole = mcRole;
	}

	public String getRole() {
		return this.role;
	}

	public static Roles resolve(String mcRole) {
		for (Roles role : values()) {
			if (role.mcRole.equals(mcRole)) {
				return role;
			}
		}
		return null;
	}

	public static String toMcRole(String appRole) {
		switch (appRole) {
		case "COMPANY":
			return "low3";
		case "EMPLOYEE":
			return "low2";
		case "MERCHANT":
			return "low1";
		default:
			return null;
		}
	}

}
