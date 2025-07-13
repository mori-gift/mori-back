package com.mori.api.config.swagger;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi(@Value("${app.server.url}") final String moriServerUrl) {
		return new OpenAPI().info(generateInfo())
			.servers(List.of(generateServer(moriServerUrl)))
			.components(generateComponents())
			.security(List.of(generateSecurityRequirement()));
	}

	private Components generateComponents() {
		return new Components()
			.addSecuritySchemes("bearerAuth", generateAccessTokenScheme())
			.addSecuritySchemes("refreshAuth", generateRefreshTokenScheme());
	}

	private SecurityRequirement generateSecurityRequirement() {
		return new SecurityRequirement()
			.addList("bearerAuth")
			.addList("refreshAuth");
	}

	private Server generateServer(final String moriServerUrl) {
		final Server server = new Server();
		server.setUrl(moriServerUrl);
		server.setDescription("Mori Gift API");
		return server;
	}

	private Info generateInfo() {
		return new Info()
			.title("Mori API")
			.version("v1.0.0")
			.description("Mori Gift 관련해서 작성된 API 문서 모음입니다.");
	}

	private SecurityScheme generateAccessTokenScheme() {
		return new SecurityScheme()
			.type(Type.HTTP)
			.in(In.HEADER)
			.name("Authorization")
			.scheme("bearer")
			.bearerFormat("JWT")
			.description("Bearer JWT");
	}

	private SecurityScheme generateRefreshTokenScheme() {
		return new SecurityScheme()
			.type(Type.APIKEY)
			.in(In.HEADER)
			.name("Refresh-Token")
			.description("Refresh Token");
	}
}
