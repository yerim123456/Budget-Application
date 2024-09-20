package com.management.budget.module.budget.dto.req;

import com.management.budget.common.enums.BudgetTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record BudgetReq(
        @Schema(description = "설정할 예산")
        Integer budget,

        @Schema(description = "저장하는 예산 타입", allowableValues = {"CATEGORY", "MONTH", "YEAR"})
        BudgetTypeEnum type,

        @Schema(description = "저장하는 예산 타입이 CATEGORY인 경우, 설정하는 카테고리 ID 전달")
        Long categoryId,

        @Schema(description = "저장하는 예산 타입이 MONTH, YEAR 경우, 설정하는 월(yyyy-MM-01)/ 년도(yyyy-01-01) 형식으로 전달")
        LocalDate at
) {
}