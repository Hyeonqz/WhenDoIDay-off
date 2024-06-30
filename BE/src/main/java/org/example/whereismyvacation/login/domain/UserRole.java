package org.example.whereismyvacation.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private String value;
}
