package com.management.budget.module.category.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryRes {
    @Schema(description = "카테고리 id", example = "1")
    Long id;

    @Schema(description = "카테고리 제목", example = "음식")
    String title;
 }