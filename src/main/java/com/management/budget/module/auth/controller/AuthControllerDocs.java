package com.management.budget.module.auth.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.dto.JwtResponse;
import com.management.budget.module.auth.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "로그인 인증 관련 API")
public interface AuthControllerDocs {
	@Operation(summary = "로그인", description = "사용자의 계정과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다.")
	@ApiResponse(responseCode = "200", description = "로그인이 성공적으로 이루어졌습니다.")
	@ApiResponse(responseCode = "401", description = "인증에 실패했습니다.")
	@ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.")
	BaseApiResponse<JwtResponse> login(LoginRequest loginRequest);
}
