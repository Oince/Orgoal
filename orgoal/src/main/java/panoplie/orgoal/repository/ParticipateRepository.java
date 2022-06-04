package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Applicant;
import panoplie.orgoal.domain.Participate;

import java.util.List;

@Repository
public interface ParticipateRepository {

    @Insert("Insert into participate values(#{aid}, #{mid}, #{applicationDate}, #{state}, #{changed}, #{answer})")
    void save(Participate participate);

    @Select("Select email, nickname, participate.answer " +
            "from member, participate " +
            "where member.mid = participate.mid and " +
            "aid = #{aid} and state = 'W'")
    List<Applicant> waitingList(int aid);

    @Insert("Update participate " +
            "set state = 'A' " +
            "where aid = #{aid} and mid = #{mid}")
    void accept(int aid, int mid);

}
