package panoplie.orgoal.controller;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.security.JwtTokenProvider;
import panoplie.orgoal.security.UserClaim;
import panoplie.orgoal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/mypage")
public class MypageController {

    private final MemberService memberService;

    @Autowired
    public MypageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/info")
    public ResponseEntity<Member> getUserMember(HttpServletRequest request) {

        String email;

        try {
            UserClaim userClaim = JwtTokenProvider.getUserClaim(request.getHeader("token"));
            email = userClaim.getEmail();
        }
        catch (MalformedJwtException | SignatureException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Member member = memberService.findByEmail(email);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(member, HttpStatus.OK);

    }
}
