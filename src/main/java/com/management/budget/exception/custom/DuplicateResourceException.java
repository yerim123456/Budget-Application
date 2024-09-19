package com.management.budget.exception.custom;

import com.management.budget.common.code.StatusCode;
import lombok.Getter;

/**
 * 생성하고자 요청하는 데이터가 이미 있는 경우
 * ex) Http Status 409
 **/
@Getter
public class DuplicateResourceException extends BusinessException {

	public DuplicateResourceException(StatusCode statusCode) {
		super(statusCode);
	}

}