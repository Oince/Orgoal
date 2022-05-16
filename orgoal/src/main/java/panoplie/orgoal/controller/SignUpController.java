package panoplie.orgoal.controller;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.service.MemberService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    private final MemberService memberService;

    @Autowired
    SignUpController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    ResponseEntity<ErrorMessage> signUp(@RequestBody SignUpForm signUpForm) {

        try {
            //회원 가입 메소드 호출
            memberService.signUp(signUpForm);
        }
        catch (DuplicateMemberException e) {
            //중복 회원이 존재할 때 409 코드 리턴
            ErrorMessage error = new ErrorMessage(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        catch (NoSuchAlgorithmException e) {
            //해시 실패시 예외 발생, 500 코드 리턴
            ErrorMessage error = new ErrorMessage(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        //정상적으로 회원 가입이 마무리되면 201 코드 리턴
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

