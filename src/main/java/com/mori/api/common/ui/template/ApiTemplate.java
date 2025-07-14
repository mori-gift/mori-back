package com.mori.api.common.ui.template;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiTemplate<T> extends NoContent {

	@Schema(description = "API 응답 결과")
	private T result;

	private ApiTemplate(boolean success, String message, T result) {
		super(success, message);
		this.result = result;
	}

	public static <T> ApiTemplate<T> ok(String responseMessage, T result) {
		return new ApiTemplate<>(true, responseMessage, result);
	}
}
