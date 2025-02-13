package com.superman.army.service;

import com.superman.army.member.Member;
import com.superman.army.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final SoldierService soldierService;

    //회원 정보 저장 메서드
    public Member saveMember(Member member) {
        return memberRepository.save(member); 
    }
    
    // 회원 정보 조회 메서드
    public Member getMemberDetails(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
    }
    
    //계급, 전역일 반영 메서드
    public void updateMember(Member member) {
        soldierService.updateMemberLevelAndDday(member);

    }
}
