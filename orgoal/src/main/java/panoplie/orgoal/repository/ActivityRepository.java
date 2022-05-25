package panoplie.orgoal.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import panoplie.orgoal.domain.Activity;

import java.util.List;

@Repository
public interface ActivityRepository {

    @Select("Select * from activity " +
            "where title like '%' || #{query} || '%' or content like '%' || #{query} || '%'")
    List<Activity> search(String query);

    @Select("Select * from activity order by last_modification desc")
    List<Activity> findAll();


    @Select("Select * from activity where aid = #{id}")
    Activity findById(int id);

    @Insert("Insert into activity values(activity_seq.nextval, #{act.title}, #{act.content}, #{act.state}, #{act.mid}, #{act.lastModification})")
    @SelectKey(statement = "SELECT activity_seq.CURRVAL FROM DUAL", keyProperty = "act.aid",
            before = false, resultType = Integer.class)
    void save(@Param("act") Activity activity);

}
