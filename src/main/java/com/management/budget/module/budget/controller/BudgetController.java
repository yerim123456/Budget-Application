package com.management.budget.module.budget.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.budget.dto.req.BudgetReq;
import com.management.budget.module.budget.service.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budgets")
public class BudgetController implements BudgetControllerDocs {

    private final BudgetService budgetService;

    /**
     * 예산 타입에 맞게 예산 설정하는 api
     **/
    @PostMapping
    @ResponseStatus(CREATED)
    public BaseApiResponse<Void> saveBudget(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid BudgetReq budgetReq) {
        String message = budgetService.saveBudget(userDetails.getMemberId(), budgetReq);
        return BaseApiResponse.of(HttpStatus.CREATED, message);
    }
}
