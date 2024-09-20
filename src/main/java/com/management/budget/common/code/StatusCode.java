package com.management.budget.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum StatusCode {

	/**
	 * 200 번대 CODE
	 **/
	OK(HttpStatus.OK, "요청이 성공했습니다."),
	CREATED(HttpStatus.CREATED, "생성되었습니다."),
	SIGN_UP_ACCEPTED(HttpStatus.ACCEPTED, "회원가입이 완료되었습니다."),

	/**
	 * 400 번대 CODE
	 **/
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 리소스를 찾을 수 없습니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "요청 경로가 지원되지 않습니다."),
	FORBIDDEN_RESOURCE_ACCESS(HttpStatus.FORBIDDEN, "해당 리소스에 대한 권한이 없습니다."),
	SHORT_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 최소 10자 이상이어야 합니다."),
	SIMPLE_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 숫자, 문자, 특수문자 중 2가지 이상을 포함해야 합니다."),
	PASSWORD_HAS_REPEATING_CHARACTER(HttpStatus.BAD_REQUEST, "비밀번호에 3회 이상 연속되는 문자 사용이 불가합니다."),
	DUPLICATE_ACCOUNT(HttpStatus.CONFLICT, "이미 사용 중인 계정입니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "요청된 사용자를 찾을 수 없습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 오류가 발생했습니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

	// 카테고리
	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "요청된 카테고리를 찾을 수 없습니다."),

	// 예산
	INVALID_BUDGET_TYPE(HttpStatus.BAD_REQUEST, "해당하는 예산 설정 타입은 없습니다."),
	BUDGET_TYPE_CATEGORY_NEED_CATEGORY_ID(HttpStatus.BAD_REQUEST, "CATEGORY 타입은 categoryId가 필요합니다."),
	BUDGET_TYPE_CATEGORY_DONT_NEED_AT(HttpStatus.BAD_REQUEST, "CATEGORY 타입은 날짜(at)를 포함하지 않아야 합니다."),
	INVALID_BUDGET_TYPE_DATE(HttpStatus.BAD_REQUEST, "YEAR, MONTH 타입의 날짜 형식에 맞게 요청해주셔야 합니다."),
	BUDGET_TYPE_DATE_NEED_AT(HttpStatus.BAD_REQUEST, "YEAR, MONTH 타입은 날짜(at)가 필요합니다."),
	BUDGET_TYPE_DATE_DONT_NEED_CATEGORY_ID(HttpStatus.BAD_REQUEST, "YEAR, MONTH 타입은 categoryId를 포함하지 않아야 합니다."),

	/**
	 * 500 번대 CODE
	 **/
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	StatusCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	/**
	 * 이름으로 StatusCode 찾고, 없다면 defaultStatusCode 로 정의하는 함수
	 **/
	public static StatusCode findStatusCodeByNameSafe(String name, StatusCode defaultStatusCode) {
		try {
			return StatusCode.valueOf(name);
		} catch (IllegalArgumentException e) {
			return defaultStatusCode;
		}
	}
}
