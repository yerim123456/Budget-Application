package com.management.budget.module.spending.service;

import com.management.budget.common.code.StatusCode;
import com.management.budget.exception.custom.BadRequestException;
import com.management.budget.exception.custom.DataNotFoundException;
import com.management.budget.module.budget.dto.res.SpendingRes;
import com.management.budget.module.category.entity.Category;
import com.management.budget.module.category.repository.CategoryRepository;
import com.management.budget.module.member.entity.Member;
import com.management.budget.module.member.repository.MemberRepository;
import com.management.budget.module.spending.dto.req.SpendingReq;
import com.management.budget.module.spending.entity.Spending;
import com.management.budget.module.spending.repository.EncouragementRepository;
import com.management.budget.module.spending.repository.SpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpendingService {
    private final SpendingRepository spendingRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 사용한 기록 저장하는 메서드
     **/
    @Transactional
    public String saveSpending(Long memberId, SpendingReq spendingReq) {

        // 멤버 가져오기
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BadRequestException(StatusCode.USER_NOT_FOUND)
        );

        // 보안을 위해 자신의 카테고리가 아니라는 말이 아닌, 카테고리 자체를 못 찾았다는 에러 메세지 발송
        Category category = categoryRepository.findByIdAndMemberId(spendingReq.categoryId(), memberId).orElseThrow(
                () -> new BadRequestException(StatusCode.CATEGORY_NOT_FOUND)
        );

        // 저장할 데이터 생성
        Spending spending = Spending.builder()
                .member(member)
                .category(category)
                .usedAmount(spendingReq.usedAmount())
                .at(spendingReq.at())
                .memo(spendingReq.memo())
                .build();

        // 데이터 저장
        spendingRepository.save(spending);

        return category.getTitle() + " 카테고리 : " + spending.getUsedAmount() + " 원 사용이 기록되었습니다.";
    }

    @Transactional
    public String updateSpending(Long memberId, Long spendingId, SpendingReq spendingReq) {

        // 기존 spending 있는지 확인하고 가져오기  / 보안을 위해 아예 찾지 못했다는 내용 설정
        Spending spending = spendingRepository.findByIdAndMemberId(spendingId, memberId).orElseThrow(
                () -> new DataNotFoundException(StatusCode.SPENDING_NOT_FOUND)
        );

        // 보안을 위해 자신의 카테고리가 아니라는 말이 아닌, 카테고리 자체를 못 찾았다는 에러 메세지 발송
        Category category = categoryRepository.findByIdAndMemberId(spendingReq.categoryId(), memberId).orElseThrow(
                () -> new BadRequestException(StatusCode.CATEGORY_NOT_FOUND)
        );

        // 항목 업데이트
        spending.updateSpending(category, spendingReq.usedAmount(), spendingReq.at(), spendingReq.memo());

        // 데이터 저장
        spendingRepository.save(spending);

        return category.getTitle() + " 카테고리 : " + spending.getUsedAmount() + " 원 " + spending.getAt() + "에 사용 / 메모: " + spending.getMemo() + " 으로 업데이트 되었습니다.";
    }

    @Transactional
    public void deleteSpending(Long memberId, Long spendingId){
        // 기존 spending 있는지 확인하고 가져오기 / 보안을 위해 아예 찾지 못했다는 내용 설정
        Spending spending = spendingRepository.findByIdAndMemberId(spendingId, memberId).orElseThrow(
                () -> new DataNotFoundException(StatusCode.SPENDING_NOT_FOUND)
        );

        spendingRepository.delete(spending);
    }

    public SpendingRes readSpendingDetail(Long memberId, Long spendingId){
        // 기존 spending 있는지 확인하고 가져오기 / 보안을 위해 아예 찾지 못했다는 내용 설정
        Spending spending = spendingRepository.findByIdAndMemberId(spendingId, memberId).orElseThrow(
                () -> new DataNotFoundException(StatusCode.SPENDING_NOT_FOUND)
        );

        // 상세보기 반환
        return SpendingRes.builder()
                .spendingId(spending.getId())
                .spendingAt(spending.getAt())
                .spendingAmount(spending.getUsedAmount())
                .spendingMemo(spending.getMemo())
                .categoryId(spending.getCategory().getId())
                .categoryTitle(spending.getCategory().getTitle())
                .build();
    }

}
