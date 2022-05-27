package panoplie.orgoal.controller;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.ibatis.javassist.NotFoundException;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.ActivityForm;
import panoplie.orgoal.security.JwtTokenProvider;
import panoplie.orgoal.security.UserClaim;
import panoplie.orgoal.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    //액티비티를 생성함, 액티비티와 토큰을 받음
    @PostMapping
    public ResponseEntity createActivity(@RequestBody ActivityForm activityForm, HttpServletRequest request) {

        int aid;
        try {
            UserClaim userClaim = JwtTokenProvider.getUserClaim(request.getHeader("token"));
            String email = userClaim.getEmail();
            aid = activityService.createActivity(activityForm, email);
        }
        catch (MalformedJwtException | SignatureException e) {
            Message message = new Message(HttpStatus.UNAUTHORIZED.value(), "Token validation failed", new Date());
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }
        catch (NotFoundException e) {
            Message message = new Message(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", aid);

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);

    }

    //액티비티에 대한 정보를 제공함, 해당 액티비티 aid와 토큰을 받음
    @GetMapping("/{aid}")
    public ResponseEntity<Activity>  getActivity(@PathVariable int aid, HttpServletRequest request) {
        Activity activity = activityService.getActivity(aid);
        if (activity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }
    }

//    //액티비티에 신청 요청을 함, 해당 액티비티 aid와 질문 답변 내용, 토큰을 받음
//    @PostMapping("/{aid}")
//    public ResponseEntity<> apply(@PathVariable String aid, @RequestBody Member member, HttpServletRequest request) {
//
//    }
//
//    //해당 액티비티에 신청자의 목록을 제공함, 해당 액티비티 aid와 토큰을 받음
//    @GetMapping("/{aid}/list")
//    public List<Activity> getApplicants(@PathVariable String aid, HttpServletRequest request) {
//
//    }
//
//    //해당 액티비티에 신청한 신청자를 수락함, 해당 액티비티 aid, 수락할 이용자의 emall, 토큰을 받음
//    @PostMapping("/{aid}/accept")
//    public ResponseEntity<> acceptApplicant(@PathVariable String aid, @RequestParam String email, HttpServletRequest request) {
//
//    }

}