package com.management.budget.module.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String account;
	private String password;
}