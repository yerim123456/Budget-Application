package com.management.budget.module.category.service;

import com.management.budget.module.category.repository.CategoryRepository;
import com.management.budget.module.category.dto.res.CategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 사용자 별 설정한 전체 카테고리 리스트 반환
     **/
    public List<CategoryRes> readMemberCategory(Long memberId){
        return categoryRepository.findAllByMemberId(memberId);
    }
}
