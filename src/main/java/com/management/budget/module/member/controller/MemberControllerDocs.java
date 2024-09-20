package com.management.budget.module.member.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.module.member.dto.request.MemberSignupReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Member", description = "멤버 관련 API")
public interface MemberControllerDocs {

    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "회원가입이 완료되었습니다.", useReturnTypeSchema = true)
    })
    BaseApiResponse<Void> postSignup(MemberSignupReq request);
}
