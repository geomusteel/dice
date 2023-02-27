package project.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.member.domain.Member;
import project.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final MemberRepository memberRepository;

    public Member login (String userId, String password) {
        Member member = memberRepository.findUserId(userId);
        if (member.getPassword().equals(password)){
            return member;
        }
        return null;
    }

}
