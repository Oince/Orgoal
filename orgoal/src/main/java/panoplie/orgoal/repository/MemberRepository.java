package panoplie.orgoal.repository;

import panoplie.orgoal.domain.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findByEmail(String email);

}
