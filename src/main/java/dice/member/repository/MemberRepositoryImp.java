package dice.member.repository;

import dice.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImp implements MemberRepository {

    Map<String, Member> memberMap = new ConcurrentHashMap<>();
    public static long sequence = 0L;


    @Override
    public void save(Member member) {
        member.setId(++sequence);
        memberMap.put(member.getUserId(), member);
    }

    @Override
    public Member findUserId(String userId) {
        if (memberMap.get(userId) != null) {
            return memberMap.get(userId);
        }
        return null;
    }
}
