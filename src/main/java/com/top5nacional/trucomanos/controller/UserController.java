package com.top5nacional.trucomanos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.top5nacional.trucomanos.dto.CreateUserDto;
import com.top5nacional.trucomanos.model.TrucomanosUser;
import com.top5nacional.trucomanos.service.UserService;
import com.top5nacional.trucomanos.utils.BindingResultManager;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	protected UserService userService;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/test")
	public ResponseEntity<List<TrucomanosUser>> test() {
		logger.info("Everything is OK");
		return userService.getAllUsers();
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserDto user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return BindingResultManager.buildErrorMessage(bindingResult);
		}
		logger.info("Attempt to POST request at /user/create");
		return userService.postOneUser(user);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> logout(@RequestParam String username) {
		logger.info("Attempt to DELETE request at /user/delete?username=" + username);
		return userService.deleteOneUser(username);
	}
}
