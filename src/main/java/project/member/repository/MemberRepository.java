package project.member.repository;

import project.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findUserId(String userId);

    List<Member> findAll();

}
