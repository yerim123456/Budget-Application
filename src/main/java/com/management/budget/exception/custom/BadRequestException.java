package com.management.budget.exception.custom;

import com.management.budget.common.code.StatusCode;
import lombok.Getter;

/**
 * 요청이 잘못된 경우
 * ex) Http status 400
 **/
@Getter
public class BadRequestException extends BusinessException {

	public BadRequestException(StatusCode statusCode) {
		super(statusCode);
	}

}