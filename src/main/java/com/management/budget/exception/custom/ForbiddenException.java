package com.management.budget.exception.custom;

import com.management.budget.common.code.StatusCode;
import lombok.Getter;

/**
 * 권한이 없는 곳에 접근하고자 하는 경우
 * ex) Http Status 403
 **/
@Getter
public class ForbiddenException extends BusinessException {

	public ForbiddenException(StatusCode statusCode) {
		super(statusCode);
	}

}