package panoplie.orgoal.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import panoplie.orgoal.repository.MemberMapper;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    void getMember() {

        System.out.println("테스트" + memberMapper.getMember());
    }
}