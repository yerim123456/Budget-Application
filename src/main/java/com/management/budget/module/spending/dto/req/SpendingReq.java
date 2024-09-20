package com.management.budget.module.spending.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record SpendingReq(
        @Schema(description = "사용한 금액")
        Integer usedAmount,

        @Schema(description = "예산을 사용한 카테고리의 ID 전달")
        Long categoryId,

        @Schema(description = "사용한 예산에 대한 메모")
        String memo,

        @Schema(description = "사용한 일자(yyyy-MM-dd hh:ss 형식)")
        LocalDateTime at
) {
}