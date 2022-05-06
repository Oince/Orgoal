package panoplie.orgoal.repository;

import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Member;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<String, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(String id) {

        for (String s : store.keySet()) {
            if (s.equals(id)) {
                return store.get(s);
            }
        }

        return null;
    }


}
