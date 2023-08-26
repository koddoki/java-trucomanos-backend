package com.top5nacional.trucomanos.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashManager {
	public String generateSalt() {
		String salt = BCrypt.gensalt();
		return salt;
	}

	public String generateHash(String password, String salt) {
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword;
	}
}
