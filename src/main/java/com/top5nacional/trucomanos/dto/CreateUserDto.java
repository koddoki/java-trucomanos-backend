package com.top5nacional.trucomanos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDto {
	@NotBlank(message = "User Name is mandatory.")
	private String username;
	@NotBlank(message = "Display Name is mandatory.")
	private String displayname;
	@NotBlank(message = "Email is mandatory.")
	@Email(message = "Invalid email address.")
	private String email;
	@NotBlank(message = "Password is mandatory.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayname;
	}

	public void setDisplayName(String displayname) {
		this.displayname = displayname;
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
}
