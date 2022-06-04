package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Participate;

@Repository
public interface ParticipateRepository {

    @Insert("Insert into participate values(#{aid}, #{mid}, #{applicationDate}, #{state}, #{changed}, #{answer})")
    void save(Participate participate);

}
