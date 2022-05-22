package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Activity;

import java.util.List;

@Repository
public interface ActivityRepository {

    @Select("Select distinct * from activity " +
            "where title like '%' || #{query} || '%' or content like '%' || #{query} || '%'")
    List<Activity> search(String query);

    @Select("Select * from activity where aid = #{id}")
    Activity findById(int id);

    @Insert("Insert into activity values(activity_seq.nextval, #{act.title}, #{act.content}, #{act.state}, #{act.mid}, #{act.lastModification})")
    void save(@Param("act") Activity activity);

}
