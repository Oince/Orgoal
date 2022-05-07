package panoplie.orgoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.service.MemberService;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    private final MemberService memberService;

    @Autowired
    SignUpController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void signUp(@RequestBody SignUpForm signUpForm) {
        memberService.signUp(signUpForm);
    }




}
