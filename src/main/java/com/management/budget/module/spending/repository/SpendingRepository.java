package com.management.budget.module.spending.repository;

import com.management.budget.module.spending.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {

    Optional<Spending> findByIdAndMemberId(Long spendingId, Long memberId);
}
