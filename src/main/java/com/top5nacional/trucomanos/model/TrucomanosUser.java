package com.top5nacional.trucomanos.model;

import com.top5nacional.trucomanos.dto.CreateUserDto;
import com.top5nacional.trucomanos.utils.HashManager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class TrucomanosUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "displayname")
	private String displayname;
	@Column(name = "password")
	private String password;
	@Column(name = "salt")
	private String salt;
	@Column(name = "email")
	private String email;

	public TrucomanosUser(CreateUserDto userDto) {
		HashManager hashManager = new HashManager();
		this.id = null;
		this.username = userDto.getUsername();
		this.displayname = userDto.getDisplayName();
		this.email = userDto.getEmail();
		this.salt = hashManager.generateSalt();
		this.password = hashManager.generateHash(userDto.getPassword(), salt);
		
		System.out.println(this.displayname);
	}

	public TrucomanosUser() {}

	public TrucomanosUser(Long id, String username, String displayname, String password, String salt, String email) {
		this.id = id;
		this.username = username;
		this.displayname = displayname;
		this.password = password;
		this.salt = salt;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}