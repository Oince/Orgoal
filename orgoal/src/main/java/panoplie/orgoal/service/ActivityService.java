package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.ActivityForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.repository.ActivityRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final MemberService memberService;

    public ActivityService(ActivityRepository activityRepository, MemberService memberService) {
        this.activityRepository = activityRepository;
        this.memberService = memberService;
    }

    public int createActivity(ActivityForm activityForm, String email) throws NotFoundException {

        Member member = memberService.getMember(email);
        if (member == null) {
            throw new NotFoundException("Not exist member");
        }
        Activity activity = new Activity(activityForm);

        activity.setMid(member.getMid());
        activity.setState('R');
        activity.setLastModification(new Date());
        activityRepository.save(activity);


        return activity.getAid();
    }

    public List<Activity> search(String query) {
        return activityRepository.search(query);
    }
    public Activity getActivity(int aid) {
        return activityRepository.findById(aid);
    }

    public List<Activity> getActivityList() {
        return activityRepository.findAll();
    }

}
