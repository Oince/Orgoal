package panoplie.orgoal.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/nickname")
public class NicknameController {
    private final MemberService memberService;

    public NicknameController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity nickname(@RequestBody Member member) {
        Member byEmail = memberService.findByEmail(member.getEmail());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nickname", byEmail.getNickname());
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }
}
