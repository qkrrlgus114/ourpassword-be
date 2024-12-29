package com.park.ourpassword.util.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiResponse<T> {

    private String status;
    private T data;
    private String message;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ERROR = "error";

    public ApiResponse(String status, T data, String message) {
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
    public static <T> ApiResponse<T> successDataMessage(T data, String message) {
        return new ApiResponse<>(SUCCESS, data, message);
    }

    /**
     * 성공(상태 + 메시지)
     */
    public static ApiResponse successMessage(String message) {
        return new ApiResponse(SUCCESS, message);
    }

    /**
     * 실패(상태 + 메시지)
     */
    public static ApiResponse failMessage(String message) {
        return new ApiResponse(FAIL, message);
    }

    /**
     * 에러(상태 + 메시지)
     */
    public static ApiResponse errorMessage(String message) {
        return new ApiResponse(ERROR, message);
    }

}
