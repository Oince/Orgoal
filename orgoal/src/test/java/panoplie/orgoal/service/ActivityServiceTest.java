package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.*;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.ActivityForm;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.repository.ActivityRepository;
import panoplie.orgoal.repository.MemoryActivityRepository;
import panoplie.orgoal.repository.MemoryMemberRepository;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class ActivityServiceTest {

    ActivityRepository activityRepository;
    ActivityService activityService;
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        memberService = new MemberService(new MemoryMemberRepository());
        activityRepository = new MemoryActivityRepository();
        activityService = new ActivityService(activityRepository, memberService);
    }

    @AfterEach
    void afterEach() {
        MemoryMemberRepository.clearStore();
        MemoryActivityRepository.clearStore();
    }

    @Test
    @DisplayName("액티비티 생성")
    void createActivity() throws NotFoundException, DuplicateMemberException, NoSuchAlgorithmException {
        SignUpForm member = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member);
        ActivityForm activityForm = new ActivityForm("축구할사람", "운동장으로 오세요");

        assertDoesNotThrow(() -> {
            activityService.createActivity(activityForm, member.getEmail());
        });
    }

    @Test
    @DisplayName("작성자가 존재하지 않는 경우")
    void notExistMember() throws DuplicateMemberException, NoSuchAlgorithmException {
        SignUpForm member = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member);
        ActivityForm activityForm = new ActivityForm("축구할사람", "운동장으로 오세요");

        assertThrows(NotFoundException.class, () -> {
            activityService.createActivity(activityForm, "hky0105");
        });
    }

    @Test
    @DisplayName("aid로 액티비티 정보 가져오기")
    void getActivity() throws DuplicateMemberException, NoSuchAlgorithmException, NotFoundException {
        SignUpForm member = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member);
        ActivityForm activityForm = new ActivityForm("축구할사람", "운동장으로 오세요");

        activityService.createActivity(activityForm, member.getEmail());

        Activity activity = activityService.getActivity(1);

        assertEquals(activityForm.getTitle(), activity.getTitle());

    }

    @Test
    @DisplayName("액티비티 가져오기 - null일 경우")
    void nullActivity() throws DuplicateMemberException, NoSuchAlgorithmException, NotFoundException {
        SignUpForm member = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member);
        ActivityForm activityForm = new ActivityForm("축구할사람", "운동장으로 오세요");
        activityService.createActivity(activityForm, member.getEmail());

        Activity activity = activityService.getActivity(2);

        assertNull(activity);

    }
}