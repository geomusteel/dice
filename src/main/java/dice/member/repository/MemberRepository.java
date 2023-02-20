package dice.member.repository;

import dice.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findUserId(String userId);

    List<Member> findAll();

}
