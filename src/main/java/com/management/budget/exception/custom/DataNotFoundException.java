package com.management.budget.exception.custom;

import com.management.budget.common.code.StatusCode;
import lombok.Getter;

/**
 * 요청 결과가 없는 경우
 * ex) Http Status 404
 **/
@Getter
public class DataNotFoundException extends BusinessException {

	public DataNotFoundException(StatusCode statusCode) {
		super(statusCode);
	}

}