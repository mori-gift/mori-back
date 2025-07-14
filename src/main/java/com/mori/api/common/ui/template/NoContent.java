package com.mori.api.common.ui.template;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "API 공통 응답")
public class NoContent {

	@Schema(description = "API 성공 여부")
	private boolean success;
	@Schema(description = "API 응답 메세지")
	private String message;

	public static NoContent ok(String message) {
		return new NoContent(true, message);
	}
}
