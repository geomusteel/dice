package project.member.repository;

import project.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImp implements MemberRepository {

    static Map<String, Member> memberMap = new ConcurrentHashMap<>();
    public static long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        memberMap.put(member.getUserId(), member);
    }

    @Override
    public Member findUserId(String userId) {
        return findAll().stream()
                .filter(m -> m.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberMap.values());
    }

}
