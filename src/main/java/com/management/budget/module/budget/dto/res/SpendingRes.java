package com.management.budget.module.budget.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class SpendingRes {
    Long spendingId;
    Long categoryId;
    String categoryTitle;
    Integer spendingAmount;
    String spendingMemo;
    LocalDateTime spendingAt;
}
