package panoplie.orgoal.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.service.MemberService;

import java.security.NoSuchAlgorithmException;

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

        String token;

        try {
            //로그인 메소드 호출
            token = memberService.signIn(loginForm);
        } catch (NotFoundException | IllegalStateException e) {
            //로그인 실패시 401 코드 반환
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (NoSuchAlgorithmException e) {
            //비밀번호 해시 과정에서 오류시 500 코드 반환
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        //정상적으로 성공시 200 코드 반환
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);

    }
}
