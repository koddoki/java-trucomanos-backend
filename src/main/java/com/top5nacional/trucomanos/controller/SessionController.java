package com.top5nacional.trucomanos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.top5nacional.trucomanos.service.SessionService;

@CrossOrigin
@RestController
public class SessionController {
	protected SessionService sessionService;
	protected static Logger logger = LoggerFactory.getLogger(SessionController.class);

	SessionController(SessionService service) {
		this.sessionService = service;
	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> userCredentials) {
		logger.info("Attempt to POST request at /user/login");
		return sessionService.login(userCredentials);
	}
}
