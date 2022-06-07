package panoplie.orgoal.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/nickname")
public class NicknameController {
    private final MemberService memberService;

    public NicknameController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity nickname(HttpServletRequest request) {
        String email = request.getHeader("email");
        Member member = memberService.getMember(email);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nickname", member.getNickname());
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }
}
