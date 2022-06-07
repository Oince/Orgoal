package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Applicant;
import panoplie.orgoal.domain.Participate;
import panoplie.orgoal.domain.ParticipatingActivity;

import java.util.List;

@Repository
public interface ParticipateRepository {

    @Select("Select * from participate " +
            "where aid = #{aid} and mid = #{mid}")
    Participate findById(@Param("aid") int aid, @Param("mid") int mid);

    @Insert("Insert into participate values(#{aid}, #{mid}, #{applicationDate}, #{state}, #{changed}, #{answer})")
    void save(Participate participate);

    @Select("Select email, nickname, participate.answer " +
            "from member, participate " +
            "where member.mid = participate.mid and " +
            "aid = #{aid} and state = 'W'")
    List<Applicant> waitingList(int aid);

    @Select("Select email, nickname " +
            "from member, participate " +
            "where member.mid = participate.mid and " +
            "aid = #{aid} and state = 'A'")
    List<Applicant> acceptList(int aid);

    @Insert("Update participate " +
            "set state = 'A' " +
            "where aid = #{aid} and mid = #{mid}")
    void accept(@Param("aid") int aid, @Param("mid") int mid);

    @Select("Select aid, title, participate.state " +
            "from activity join participate using(aid) " +
            "where participate.mid = #{mid}")
    List<ParticipatingActivity> participatingList(int mid);

}
