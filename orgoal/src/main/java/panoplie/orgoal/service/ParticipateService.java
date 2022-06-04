package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.*;
import panoplie.orgoal.repository.ParticipateRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
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

    public List<Applicant> getApplicants(String email, int aid) throws IllegalAccessException, NotFoundException {

        Member member = memberService.getMember(email);
        Activity activity = activityService.getActivity(aid);
        if (activity == null) {
            throw new NotFoundException("Not exist");
        }
        if (member.getMid() != activity.getMid()) {
            throw new IllegalAccessException("Access failed");
        }

        return participateRepository.waitingList(aid);

    }

    public void acceptMember(int aid, String email) {

    }

    public List<ParticipatingActivity> getParticipateList(int mid) {
        return participateRepository.participatingList(mid);
    }
}
