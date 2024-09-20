package com.management.budget.module.spending.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.common.code.StatusCode;
import com.management.budget.module.auth.security.CustomUserDetails;
import com.management.budget.module.budget.dto.res.SpendingRes;
import com.management.budget.module.spending.dto.req.SpendingReq;
import com.management.budget.module.spending.service.SpendingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spendings")
public class SpendingController implements SpendingControllerDocs {

    private final SpendingService spendingService;

    /**
     * 지출 내역 저장 api
     **/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseApiResponse<Void> saveSpending(@AuthenticationPrincipal CustomUserDetails userDetails, SpendingReq spendingReq) {
        String message = spendingService.saveSpending(userDetails.getMemberId(), spendingReq);
        return BaseApiResponse.of(OK, message);
    }

    /**
     * 지출 내역 수정 api
     **/
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{spendingId}")
    public BaseApiResponse<Void> updateSpending(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "spendingId") Long spendingId, @RequestBody @Valid SpendingReq spendingReq) {
        String message = spendingService.updateSpending(userDetails.getMemberId(), spendingId, spendingReq);
        return BaseApiResponse.of(OK, message);
    }

    /**
     * 지출 내역 삭제 api
     **/
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{spendingId}")
    public BaseApiResponse<Void> deleteSpending(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "spendingId") Long spendingId) {
        spendingService.deleteSpending(userDetails.getMemberId(), spendingId);
        return BaseApiResponse.of(StatusCode.OK);
    }

    /**
     * 지출 내역 상세 보기 api
     **/
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{spendingId}")
    public BaseApiResponse<SpendingRes> readSpendingDetail(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "spendingId") Long spendingId) {
        return BaseApiResponse.of(StatusCode.OK, spendingService.readSpendingDetail(userDetails.getMemberId(), spendingId));
    }
}
