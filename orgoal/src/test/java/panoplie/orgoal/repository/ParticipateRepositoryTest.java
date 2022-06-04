package panoplie.orgoal.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.Applicant;
import panoplie.orgoal.domain.Participate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ParticipateRepositoryTest {

    @Autowired
    ParticipateRepository participateRepository;

    @Test
    @DisplayName("액티비티 참가 정보 저장")
    void save() {
        Participate participate = new Participate(61, 21, new Date(), 'W', 'T', "안녕하세요");
        assertDoesNotThrow(() -> participateRepository.save(participate));
    }

    @Test
    @DisplayName("신청자 목록")
    void waitingList() {
        int aid = 43;
        List<Applicant> applicants = participateRepository.waitingList(43);
        assertEquals(applicants.get(0).getAnswer(), "안녕하세요");
    }
}