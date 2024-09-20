package com.management.budget.module.member.service;

import com.management.budget.common.code.StatusCode;
import com.management.budget.exception.custom.DuplicateResourceException;
import com.management.budget.module.member.dto.request.MemberSignupReq;
import com.management.budget.module.member.entity.Member;
import com.management.budget.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void postSignup(MemberSignupReq request) {
        // 계정 중복 검사
        validateAccountUnique(request.account());

        // 회원 생성 및 저장
        Member member = Member.signUp(request.account(), request.password(), passwordEncoder);
        memberRepository.save(member);
    }

    private void validateAccountUnique(String account) {
        if (memberRepository.existsByAccount(account)) {
            throw new DuplicateResourceException(StatusCode.DUPLICATE_ACCOUNT);
        }
    }
}
