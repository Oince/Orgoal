package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(SignUpForm signUpForm) {
        Member member = new Member(signUpForm);
        memberRepository.save(member);

    }

    public Member signIn(LoginForm loginForm) {
        Member member = memberRepository.findByEmail(loginForm.getEmail());
        if (member == null) {
            return null;
        }
        if (member.getPasswd().equals(loginForm.getPasswd())) {
            return member;
        } else {
            return null;
        }
    }

}
