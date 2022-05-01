package panoplie.orgoal.repository;

import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Member;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findByEmail(String email) {
        for (Member member : store.values()) {
            if (member.getEmail().equals(email)) {
                return member;
            }
        }
        return null;
    }
}
