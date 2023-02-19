package dice.member.repository;

import dice.member.domain.Member;

public interface MemberRepository {
    void save(Member member);
    Member findUserId(String userId);

}
