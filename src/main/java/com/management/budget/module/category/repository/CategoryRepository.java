package com.management.budget.module.category.repository;

import com.management.budget.module.category.dto.res.CategoryRes;
import com.management.budget.module.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * 사용자 id 및 카테고리 id 로 본인 카테고리 가져오는 JPA 메서드
     **/
    Optional<Category> findByIdAndMemberId(Long categoryId, Long memberId);

    /**
     * 사용자 id 기반 카테고리 전체 리스트 찾는 JPA 메서드
     **/
    @Query("SELECT new com.management.budget.module.category.dto.res.CategoryRes(c.id, c.title) " +
            "FROM Category c WHERE c.member.id = :memberId")
    List<CategoryRes> findAllByMemberId(Long memberId);
}
