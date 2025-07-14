package com.mori.api.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionMessage {

	HttpStatus getHttpStatus();

	String getMessage();
}
