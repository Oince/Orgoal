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
import org.springframework.security.crypto.password.PasswordEncoder;

import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.service.MemberService;
import panoplie.orgoal.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class SignInController {

    private final MemberService memberService;

    @Autowired
    public SignInController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        Member member = memberService.signIn(loginForm);
//        HttpSession session = request.getSession();
        if (member == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return jwtTokenProvider.createToken(member.getEmail(), member.get);
        }


    }
}
