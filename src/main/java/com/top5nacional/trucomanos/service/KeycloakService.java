package com.top5nacional.trucomanos.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {

    private final String tokenUrl = "http://localhost:8083/realms/top5nacional/protocol/openid-connect/token";
    private final String introspectUrl = "http://localhost:8083/realms/top5nacional/protocol/openid-connect/token/introspect";
    private final String clientId = "top-5-nacional";
    protected final String clientSecret = "LE8fYrDY82yjC4YHVHMaeBTrzAfMFLSB";

    public ResponseEntity<String> createUser(String accessToken, @RequestBody MultiValueMap<String, String> bodyParams) {
    	RestTemplate restTemplate = new RestTemplate();

        bodyParams.add("client_id", clientId);
        bodyParams.add("client_secret", clientSecret);
        bodyParams.add("grant_type", "client_credentials");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyParams, headers);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        } else {
            throw new RuntimeException("Erro ao obter o token: " + responseEntity.getStatusCode());
        }
    }
    public ResponseEntity<String> login(MultiValueMap<String, String> bodyParams) {
    	RestTemplate restTemplate = new RestTemplate();

        bodyParams.add("client_id", clientId);
        bodyParams.add("client_secret", clientSecret);
        bodyParams.add("grant_type", "client_credentials");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyParams, headers);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        } else {
            throw new RuntimeException("Erro ao obter o token: " + responseEntity.getStatusCode());
        }
    }
    public ResponseEntity<String> validate_access_token(MultiValueMap<String, String> bodyParams) {
    	if (bodyParams == null) {
    		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("EU NÃO TE CONHEÇO!!!!!"); 
    	}
    	
    	
    	RestTemplate restTemplate = new RestTemplate();

        bodyParams.add("client_id", clientId);
        bodyParams.add("client_secret", clientSecret);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyParams, headers);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(introspectUrl, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null && !responseEntity.getBody().equals("{\"active\":false}")) {
        	System.out.println(responseEntity.getBody());
            return ResponseEntity.ok("Pode jogar, meu chapa!");
        } else {
        	 return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("EU NÃO TE CONHEÇO!!!!!");
        }
    }
}
