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

import com.top5nacional.trucomanos.service.KeycloakService;

@CrossOrigin
@RestController
public class KeycloakController {
	protected KeycloakService keycloakService;
	protected static Logger logger = LoggerFactory.getLogger(KeycloakController.class);

	KeycloakController(KeycloakService service) {
		this.keycloakService = service;
	}

	@PostMapping(path = "/keycloak/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> userCredentials) {
		logger.info("Attempt to POST request at /keycloak/login");
		return keycloakService.login(userCredentials);
	}
	
	@PostMapping(path = "/keycloak/introspection", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> introspection(@RequestBody MultiValueMap<String, String> token) {
		logger.info("Attempt to POST request at /keycloak/login");
		return keycloakService.validate_access_token(token);
	}
	
	@PostMapping(path = "/keycloak/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> create(@RequestBody MultiValueMap<String, String> userCredentials) {
		logger.info("Attempt to POST request at /keycloak/create");
		return null;
	}
}
