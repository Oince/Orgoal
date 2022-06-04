package panoplie.orgoal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ActivityService activityService;

    public SearchController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> search(@RequestParam String query) {
        List<Activity> list = activityService.search(query);

        if (list.isEmpty()) {
            list = activityService.getActivityList();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
