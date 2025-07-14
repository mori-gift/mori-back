package com.mori.api.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ExceptionMessage exceptionMessage;

	public BusinessException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage.getMessage());
		this.exceptionMessage = exceptionMessage;
	}
}
