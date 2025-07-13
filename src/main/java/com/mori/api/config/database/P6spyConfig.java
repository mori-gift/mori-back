package com.mori.api.config.database;

import org.springframework.context.annotation.Configuration;

import com.p6spy.engine.spy.P6SpyOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class P6spyConfig {

	@PostConstruct
	public void setLogMessageFormat() {
		P6SpyOptions.getActiveInstance()
			.setLogMessageFormat(CustomP6spySqlFormat.class.getName());
	}
}
