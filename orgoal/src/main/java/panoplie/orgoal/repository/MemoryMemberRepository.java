package panoplie.orgoal.repository;

import panoplie.orgoal.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//테스트용 메모리DB
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Integer, Member> store = new HashMap<>();
    private static int mid = 0;

    @Override
    public void save(Member member) {
        member.setMid(++mid);
        store.put(member.getMid(), member);
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

    public static void clearStore() {
        mid = 0;
        store.clear();
    }


}
