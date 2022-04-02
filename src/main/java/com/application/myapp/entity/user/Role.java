package com.application.myapp.entity.user;

import java.util.Set;

public enum Role {
	ROLE_USER(Set.of(
		Permission.READ_ANY_PROFILE,
		Permission.UPDATE_YOUR_PROFILE)),
	
	ROLE_ADMIN(Set.of(
		Permission.CREATE_PROFILE,
		Permission.READ_ANY_PROFILE,
		Permission.UPDATE_YOUR_PROFILE,
		Permission.UPDATE_ANY_PROFILE,
		Permission.DELETE_ANY_PROFILE)),

	ROLE_SUPERADMIN(Set.of(
		Permission.CREATE_PROFILE,
		Permission.READ_ANY_PROFILE,
		Permission.UPDATE_YOUR_PROFILE,
		Permission.UPDATE_ANY_PROFILE,
		Permission.DELETE_ANY_PROFILE,
		Permission.EDIT_USER_RIGHTS));

	private Set<Permission> permissions;

	Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
}