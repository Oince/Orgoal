package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import panoplie.orgoal.domain.Member;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("Select * from member")
    List<Member> getMember();

    @Insert("Insert into member values('123','tom','sdfsdf','FAVORITECOLOR','blue')")
    void saveMember();
}
