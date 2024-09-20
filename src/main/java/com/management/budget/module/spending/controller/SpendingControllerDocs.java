package com.management.budget.module.spending.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.budget.dto.res.SpendingRes;
import com.management.budget.module.spending.dto.req.SpendingReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Spending", description = " 지출 내역 관련 API")
public interface SpendingControllerDocs {

    @Operation(summary = "지출 내역 저장", description = "자신이 지출한 내역을 저장합니다.")
    @ApiResponse(responseCode = "201", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<Void> saveSpending(
            CustomUserDetails userDetails,
            SpendingReq spendingReq
    );

    @Operation(summary = "지출 내역 수정", description = "자신이 지출한 내역을 수정합니다..")
    @ApiResponse(responseCode = "200", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<Void> updateSpending(
            CustomUserDetails userDetails,
            Long spendingId,
            SpendingReq spendingReq
    );

    @Operation(summary = "지출 내역 제거", description = "자신이 지출한 내역을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<Void> deleteSpending(
            CustomUserDetails userDetails,
            Long spendingId
    );

    @Operation(summary = "지출 내역 상세 보기", description = "자신이 지출한 내역을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "요청이 성공했습니다.", useReturnTypeSchema = true)
    BaseApiResponse<SpendingRes> readSpendingDetail(
            CustomUserDetails userDetails,
            Long spendingId
    );
}

