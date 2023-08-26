package com.top5nacional.trucomanos.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class BindingResultManager {
	public static ResponseEntity<String> buildErrorMessage(BindingResult bindingResult) {
		StringBuilder errorMessage = new StringBuilder();
		bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
		return ResponseEntity.badRequest().body(errorMessage.toString());
	}
}