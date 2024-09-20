package com.management.budget.module.member.repository;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByAccount(String account);

	Optional<Member> findByAccount(String account);

}
