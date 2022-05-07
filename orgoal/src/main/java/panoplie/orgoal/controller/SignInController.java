package panoplie.orgoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/signin")
public class SignInController {

    private final MemberService memberService;

    @Autowired
    public SignInController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginForm loginForm) {
        Member member = memberService.signIn(loginForm);

        if (member == null) {
            System.out.println("실패");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("성공");
            return new ResponseEntity(HttpStatus.OK);
        }


    }
}
