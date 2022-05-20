package panoplie.orgoal.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.Activity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ActivityRepositoryTest {

    @Autowired
    ActivityRepository activityRepository;

    //더미 데이터
    //Activity activity = new Activity(21, "모각코 모집", "모각코 할사람 구해요\n 아무나 환영", 'R', 21, new Date());

    @Test
    @DisplayName("액티비티 저장")
    void save() {
        Activity activity = new Activity("모각코 모집", "모각코 할사람 구해요\n 아무나 환영", 'R', 21, new Date());

        activityRepository.save(activity);
    }

    @Test
    @DisplayName("액티비티id로 찾기")
    void findById() {
        Activity activity = new Activity("모각코 모집", "모각코 할사람 구해요\n 아무나 환영", 'R', 21, new Date());
        activity.setAid(21);

        Activity id = activityRepository.findById(activity.getAid());

        assertEquals(activity.getContent(), id.getContent());
        assertEquals(activity.getTitle(), id.getTitle());

    }

    @Test
    @DisplayName("query 검색")
    void search() {
        String query = "모각코";
        List<Activity> list = activityRepository.search(query);
        for (Activity activity : list) {
            System.out.println(activity);
            assertDoesNotThrow(() -> {
                if (!(activity.getTitle().contains(query) || activity.getContent().contains(query))) {
                    throw new Exception();
                }
            });
        }
    }
}