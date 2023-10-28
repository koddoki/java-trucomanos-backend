package com.top5nacional.trucomanos.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> {
			try {
				authorize.anyRequest().permitAll();
				//.requestMatchers("/login").permitAll()
				//.anyRequest().authenticated().and().oauth2ResourceServer().jwt();
			} catch (Exception e) {
				logger.error("Exception at @Bean SecurityFilterChain: {}", e);
			}
		})
		.cors(withDefaults())
		.csrf(csrf -> csrf.disable())
		.logout((logout -> logout.disable()))
		.httpBasic(withDefaults());
		return http.build();
	}
}