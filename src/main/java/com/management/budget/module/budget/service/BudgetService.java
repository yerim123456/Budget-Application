package com.management.budget.module.budget.service;

import com.management.budget.common.code.StatusCode;
import com.management.budget.common.enums.BudgetTypeEnum;
import com.management.budget.exception.custom.BadRequestException;
import com.management.budget.module.budget.dto.req.BudgetReq;
import com.management.budget.module.budget.entity.Budget;
import com.management.budget.module.budget.repository.BudgetRepository;
import com.management.budget.module.category.entity.Category;
import com.management.budget.module.category.repository.CategoryRepository;
import com.management.budget.module.member.entity.Member;
import com.management.budget.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BudgetService {
    private final CategoryRepository categoryRepository;
    private final BudgetRepository budgetRepository;
    private final MemberRepository memberRepository;

    /**
     * 타입 별 예산 설정
     **/
    @Transactional
    public String saveBudget(Long memberId, BudgetReq budgetReq) {
        // 설정하는 예산 타입에 맞게 필요한 데이터 validation 체크
        Category category = checkValidateByBudgetType(budgetReq, memberId);

        // 멤버 가져오기
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BadRequestException(StatusCode.USER_NOT_FOUND)
        );

        // 데이터 생성
        Budget budget = Budget.builder()
                .member(member)
                .budget(budgetReq.budget())
                .budgetType(budgetReq.type())
                .category(category)
                .at(budgetReq.at())
                .build();

        // 저장
        budgetRepository.save(budget);

        return budget.getBudgetType() + "타입의 예산이 " + budget.getBudget() + "으로 설정되었습니다.";
    }


    /**
     * 예산 타입 별 유효성 검증 메서드
     **/
    private Category checkValidateByBudgetType(BudgetReq budgetReq, Long memberId) {
        switch (budgetReq.type()) {
            case CATEGORY-> {
                checkValidateCategoryId(budgetReq);

                // 보안을 위해 자신의 카테고리가 아니라는 말이 아닌, 카테고리 자체를 못 찾았다는 에러 메세지 발송
                return categoryRepository.findByIdAndMemberId(budgetReq.categoryId(), memberId).orElseThrow(
                        () -> new BadRequestException(StatusCode.CATEGORY_NOT_FOUND)
                );
            }
            case MONTH, YEAR -> {
                checkValidateDateFormat(budgetReq);
            }
            default -> {
                throw new BadRequestException(StatusCode.INVALID_BUDGET_TYPE);
            }
        }
        return null;
    }


    /**
     * type 이 category인 경우, 관련 유효성 검증 진행 메서드
     **/
    private void checkValidateCategoryId(BudgetReq budgetReq) {
        // 카테고리 id가 오지 않은 경우
        if (budgetReq.categoryId() == null) {
            throw new BadRequestException(StatusCode.BUDGET_TYPE_CATEGORY_NEED_CATEGORY_ID);
        }

        // 날짜가 온 경우
        if (budgetReq.at() != null) {
            throw new BadRequestException(StatusCode.BUDGET_TYPE_CATEGORY_DONT_NEED_AT);
        }
    }

    /**
     * type 이 year, month 경우, 관련 유효성 검증 진행 메서드
     **/
    private void checkValidateDateFormat(BudgetReq budgetReq) {
        // 날짜가 오지 않은 경우
        if (budgetReq.at() == null) {
            throw new BadRequestException(StatusCode.BUDGET_TYPE_DATE_NEED_AT);
        }

        // 카테고리 id 가 온 경우
        if (budgetReq.categoryId() != null) {
            throw new BadRequestException(StatusCode.BUDGET_TYPE_DATE_DONT_NEED_CATEGORY_ID);
        }

        // YEAR, MONTH 에 해당하는 형식과 맞지 않을 경우
        // YEAR > yyyy-01-01
        // MONTH > yyyy-MM-01
        if ((budgetReq.type() == BudgetTypeEnum.YEAR && !budgetReq.at().toString().endsWith("01-01")) ||
                (budgetReq.type() == BudgetTypeEnum.MONTH && budgetReq.at().getDayOfMonth() != 1)) {
            throw new BadRequestException(StatusCode.INVALID_BUDGET_TYPE_DATE);
        }
    }
}
