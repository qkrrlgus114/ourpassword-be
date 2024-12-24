package com.park.ourpassword.util.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ApiResponse {

	private String status;
	private Object data;
	private String message;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";

	public ApiResponse(String status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public ApiResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * 성공(상태 + 데이터 + 메시지)
	 */
	public static ApiResponse successDataMessage(Object data, String message) {
		return ApiResponse.builder()
			.status(SUCCESS)
			.data(data)
			.message(message).build();
	}

	/**
	 * 성공(상태 + 메시지)
	 */
	public static ApiResponse successMessage(String message) {
		return ApiResponse.builder()
			.status(SUCCESS)
			.message(message).build();
	}

	/**
	 * 실패(상태 + 메시지)
	 */
	public static ApiResponse failMessage(String message) {
		return ApiResponse.builder()
			.status(FAIL)
			.message(message).build();
	}

	/**
	 * 에러(상태 + 메시지)
	 */
	public static ApiResponse errorMessage(String message) {
		return ApiResponse.builder()
			.status(ERROR)
			.message(message).build();
	}

}
