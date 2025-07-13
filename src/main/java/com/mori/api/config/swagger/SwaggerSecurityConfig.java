package com.mori.api.config.swagger;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerSecurityConfig {

	private final String swaggerRole = "SWAGGER";
	private final String[] swaggerPaths = {"/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**"};

	@Bean
	public UserDetailsService createSwaggerUser(
		@Value("${app.swagger.id}") final String swaggerId,
		@Value("${app.swagger.pw}") final String swaggerPw,
		final PasswordEncoder passwordEncoder
	) {
		return new InMemoryUserDetailsManager(
			User.withUsername(swaggerId)
				.password(passwordEncoder.encode(swaggerPw))
				.roles(swaggerRole)
				.build()
		);
	}

	@Bean
	public SecurityFilterChain swaggerSecurityFilterChain(
		final HttpSecurity http,
		final UserDetailsService userDetailsService
	) throws Exception {
		return http
			.httpBasic(Customizer.withDefaults())
			.securityMatcher(swaggerPaths)
			.authorizeHttpRequests(auth -> auth.requestMatchers(swaggerPaths).hasRole(swaggerRole))
			.userDetailsService(userDetailsService)
			.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
			.csrf(AbstractHttpConfigurer::disable)
			.build();
	}
}
