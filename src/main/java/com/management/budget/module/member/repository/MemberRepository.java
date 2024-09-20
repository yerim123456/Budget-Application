package com.management.budget.module.member.repository;

import java.util.Optional;

import com.management.budget.module.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByAccount(String account);

	Optional<Member> findByAccount(String account);

}
