package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.domain.Participate;
import panoplie.orgoal.repository.ParticipateRepository;

import java.util.Date;

@Service
public class ParticipateService {

    private final ParticipateRepository participateRepository;
    private final MemberService memberService;
    private final ActivityService activityService;

    public ParticipateService(ParticipateRepository participateRepository, MemberService memberService, ActivityService activityService) {
        this.participateRepository = participateRepository;
        this.memberService = memberService;
        this.activityService = activityService;
    }

    public void application(int aid, String email, String answer) throws NotFoundException, DuplicateKeyException {

        Member applicant = memberService.getMember(email);
        Activity activity = activityService.getActivity(aid);
        if (applicant == null || activity == null) {
            throw new NotFoundException("Not exist");
        }

        Participate participate = new Participate(aid, applicant.getMid(), new Date(), 'W', 'T', answer);

        participateRepository.save(participate);
    }
}
