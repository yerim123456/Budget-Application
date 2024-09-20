package com.management.budget.module.member.controller;

import com.management.budget.common.BaseApiResponse;
import com.management.budget.common.code.StatusCode;
import com.management.budget.module.member.dto.request.MemberSignupReq;
import com.management.budget.module.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(ACCEPTED)
    public BaseApiResponse<Void> postSignup(
            @RequestBody @Valid MemberSignupReq request) {
        memberService.postSignup(request);
        return BaseApiResponse.of(StatusCode.SIGN_UP_ACCEPTED);
    }
}