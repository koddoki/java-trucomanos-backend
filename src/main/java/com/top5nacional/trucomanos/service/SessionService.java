package com.top5nacional.trucomanos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.top5nacional.trucomanos.model.TrucomanosUser;
import com.top5nacional.trucomanos.repository.TrucomanosUserRepository;
import com.top5nacional.trucomanos.utils.HashManager;

@Service
public class SessionService {
	@Autowired
	protected TrucomanosUserRepository userRepository;
	protected HashManager hashManager = new HashManager();

	public ResponseEntity<String> login(MultiValueMap<String, String> userCredentials) {
		if (!userCredentials.containsKey("username") && !userCredentials.containsKey("password")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Optional<TrucomanosUser> user = userRepository.findByUsername(userCredentials.getFirst("username"));

		if (user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String loginHash = hashManager.generateHash(userCredentials.getFirst("password"), user.get().getSalt());

		if (user.get().getPassword().equals(loginHash)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}