package com.management.budget.module.auth.security;

import com.management.budget.common.code.StatusCode;
import com.management.budget.module.member.entity.Member;
import com.management.budget.module.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		Member member = memberRepository.findByAccount(account)
			.orElseThrow(() -> new UsernameNotFoundException(StatusCode.UNAUTHORIZED.name()));

		log.info("유저 조회 성공: {}", member.getAccount());
		log.info("저장된 비밀번호: {}", member.getPassword());

		return new CustomUserDetails(member);
	}
}
