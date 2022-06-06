package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.ParticipatingActivity;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class MypageService {

    private final ActivityService activityService;
    private final ParticipateService participateService;

    @Autowired
    public MypageService(ActivityService activityService, ParticipateService participateService) {
        this.activityService = activityService;
        this.participateService = participateService;
    }

    public List<List<ParticipatingActivity>> getMyActivity(int mid) {

        List<ParticipatingActivity> activityList = activityService.getActivityList(mid);
        List<ParticipatingActivity> participateList = participateService.getParticipateList(mid);

        List<List<ParticipatingActivity>> list = new LinkedList<>();
        list.add(activityList);
        list.add(participateList);

        return list;

    }

}
