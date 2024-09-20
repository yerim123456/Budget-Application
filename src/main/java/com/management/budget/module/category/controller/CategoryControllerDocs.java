package com.management.budget.module.category.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.category.dto.res.CategoryRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Category", description = "카테고리 관련 API")
public interface CategoryControllerDocs {

    @Operation(summary = "자신의 카테고리 조회", description = "사용자 별로 설정한 자신의 카테고리를 모두 보여줍니다.")
    @ApiResponse(responseCode = "200", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<List<CategoryRes>> readMemberCategory(
            CustomUserDetails userDetails
    );
}

