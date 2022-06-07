package panoplie.orgoal.controller;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.ibatis.javassist.NotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panoplie.orgoal.domain.Activity;
import panoplie.orgoal.domain.ActivityForm;
import panoplie.orgoal.domain.Applicant;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.security.JwtTokenProvider;
import panoplie.orgoal.security.UserClaim;
import panoplie.orgoal.service.ActivityService;
import panoplie.orgoal.service.MemberService;
import panoplie.orgoal.service.ParticipateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;
    private final ParticipateService participateService;
    private final MemberService memberService;

    private final JSONParser jsonParser = new JSONParser();

    @Autowired
    public ActivityController(ActivityService activityService, ParticipateService participateService, MemberService memberService) {
        this.activityService = activityService;
        this.participateService = participateService;
        this.memberService = memberService;
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
            return new ResponseEntity<>("Token validation failed", HttpStatus.UNAUTHORIZED);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", aid);

        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);

    }

    //액티비티에 대한 정보를 제공함, 해당 액티비티 aid와 토큰을 받음
    @GetMapping("/{aid}")
    public ResponseEntity<JSONObject>  getActivity(@PathVariable int aid) {

        Activity activity = activityService.getActivity(aid);
        if (activity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Member member = memberService.getMember(activity.getMid());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", activity.getAid());
        jsonObject.put("title", activity.getTitle());
        jsonObject.put("content", activity.getContent());
        jsonObject.put("state", activity.getState());
        jsonObject.put("email", member.getEmail());
        jsonObject.put("nickname", member.getNickname());
        jsonObject.put("lastModification", activity.getLastModification());
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);

    }

    //액티비티에 신청 요청을 함, 해당 액티비티 aid와 질문 답변 내용, 토큰을 받음
    @PostMapping("/{aid}")
    public ResponseEntity<String> apply(@PathVariable int aid, @RequestBody String answer, HttpServletRequest request)  {

        UserClaim userClaim = JwtTokenProvider.getUserClaim(request.getHeader("token"));
        String email = userClaim.getEmail();

        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(answer);
            Object answer1 = jsonObject.get("answer");
            participateService.application(aid, email, (String) answer1);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Duplicated application", HttpStatus.CONFLICT);
        } catch (ParseException e) {
            return new ResponseEntity<>("Illegal request", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    //해당 액티비티에 신청자의 목록을 제공함, 해당 액티비티 aid와 토큰을 받음
    @GetMapping("/{aid}/list")
    public ResponseEntity getApplicants(@PathVariable int aid, HttpServletRequest request) {
        String email = JwtTokenProvider.getUserClaim(request.getHeader("token")).getEmail();
        List<Applicant> applicants;
        try {
            applicants = participateService.getApplicants(email, aid);
        } catch (IllegalAccessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(applicants, HttpStatus.OK);
    }

    //해당 액티비티 참가자 목록
    @GetMapping("/{aid}/member")
    public ResponseEntity getParticipant(@PathVariable int aid) {
        List<Applicant> acceptList = participateService.getAcceptList(aid);
        return new ResponseEntity<>(acceptList, HttpStatus.OK);
    }

    //해당 액티비티에 신청한 신청자를 수락함, 해당 액티비티 aid, 수락할 이용자의 emall, 토큰을 받음
    @PostMapping("/{aid}/accept")
    public ResponseEntity<String> acceptApplicant(@PathVariable int aid, @RequestParam String email, HttpServletRequest request)  {
        String hostEmail = JwtTokenProvider.getUserClaim(request.getHeader("token")).getEmail();
        try {
            participateService.acceptMember(aid, email, hostEmail);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
