package com.management.budget.exception.custom;

import com.management.budget.common.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 비즈니스 로직 중에서 나는 에러 정의
 **/
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

	private final StatusCode statusCode;
	private String message;

	public BusinessException(StatusCode statusCode) {
		this.statusCode = statusCode;
		this.message = statusCode.getMessage();
	}
}