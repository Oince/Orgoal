package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.repository.ActivityRepository;

import java.util.Date;

@Service
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final MemberService memberService;

    public ActivityService(ActivityRepository activityRepository, MemberService memberService) {
        this.activityRepository = activityRepository;
        this.memberService = memberService;
    }

    public void createActivity(Activity activity, String email) throws NotFoundException {

        Member member = memberService.findByEmail(email);
        if (member == null) {
            throw new NotFoundException("Not exist member");
        }

        activity.setMid(member.getMid());
        activity.setState('R');
        activity.setLastModification(new Date());
        activityRepository.save(activity);
    }

    public Activity getActivity(int aid) {
        Activity activity = activityRepository.findById(aid);

        return activity;

    }

}
