package com.management.budget.module.auth.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.dto.JwtResponse;
import com.management.budget.module.auth.dto.LoginRequest;
import com.management.budget.module.auth.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocs {

	private final AuthenticationManager authenticationManager;

	private final JwtManager jwtManager;

	@PostMapping("/login")
	public BaseApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {

		log.info("로그인 시도: {}", loginRequest.getAccount());

		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getAccount(), loginRequest.getPassword()
			)
		);
		log.info("AuthenticationManager 호출 후 - 인증 성공");

		// 문제 없을 시 JWT 토큰 생성
		String token = jwtManager.generateToken(authentication);
		log.info("JWT 생성: {}", token);

		log.info("로그인 성공: {}", loginRequest.getAccount());
		JwtResponse jwtRes = new JwtResponse(token);
		return BaseApiResponse.of(HttpStatus.OK, "로그인에 성공했습니다.", jwtRes);

	}
}
