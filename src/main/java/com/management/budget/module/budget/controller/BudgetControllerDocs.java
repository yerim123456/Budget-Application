package com.management.budget.module.budget.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.budget.dto.req.BudgetReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Budget", description = " 예산 관련 API")
public interface BudgetControllerDocs {

    @Operation(summary = "예산 설정", description = "월, 년, 카테고리 type에 따른 예산을 설정합니다.")
    @ApiResponse(responseCode = "200", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<Void> saveBudget(
            CustomUserDetails userDetails,
            BudgetReq budgetReq
    );
}

