package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Member;

import java.util.List;

@Repository
//DB의 member 테이블에 접근
public interface MemberRepository {

    //회원 가입시 member 테이블에 삽입
    @Insert("Insert into member values(member_seq.nextval,#{member.email},#{member.password},#{member.nickname},#{member.question},#{member.answer})")
    void save(@Param("member") Member member);

    //email이 같은 member 찾기
    @Select("Select * from member " +
            "where email = #{email}")
    Member findByEmail(String email);

}
