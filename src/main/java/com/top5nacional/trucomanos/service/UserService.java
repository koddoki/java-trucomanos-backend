package com.top5nacional.trucomanos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.top5nacional.trucomanos.dto.CreateUserDto;
import com.top5nacional.trucomanos.model.TrucomanosUser;
import com.top5nacional.trucomanos.repository.TrucomanosUserRepository;

@Service
public class UserService {
	@Autowired
	protected TrucomanosUserRepository userRepository;

	public ResponseEntity<List<TrucomanosUser>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}

	public ResponseEntity<String> postOneUser(CreateUserDto userDto) {
		if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("User " + userDto.getUsername() + " already exist in the database.");
		}
		if (userRepository.findByUsername(userDto.getDisplayName()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Displayname " + userDto.getDisplayName() + " already exist in the database.");
		}
		if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("E-mail " + userDto.getEmail() + " already exist in the database.");
		}

		TrucomanosUser user = new TrucomanosUser(userDto);

		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteOneUser(String username) {
		Optional<TrucomanosUser> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userRepository.delete(user.get());
		return ResponseEntity.status(HttpStatus.OK).body(username + " was deleted succesfully.");
	}
}
