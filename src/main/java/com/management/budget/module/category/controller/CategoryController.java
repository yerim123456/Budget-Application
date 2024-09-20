package com.management.budget.module.category.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.category.dto.res.CategoryRes;
import com.management.budget.module.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController implements CategoryControllerDocs {

	private final CategoryService categoryService;

	/**
	 * 사용자 별 설정한 전체 카테고리 리스트 반환 api
	 **/
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public BaseApiResponse<List<CategoryRes>> readMemberCategory(@AuthenticationPrincipal CustomUserDetails userDetails) {

		List<CategoryRes> categories = categoryService.readMemberCategory(userDetails.getMemberId());

		return BaseApiResponse.of(OK, categories);
	}
}
