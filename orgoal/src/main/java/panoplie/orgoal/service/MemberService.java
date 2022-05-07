package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.repository.MemberRepository;

import java.security.MessageDigest;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(Member member) {

        memberRepository.save(member);
    }

    public Member signIn(LoginForm loginForm) {
        Member member = memberRepository.findById(loginForm.getId());
        if (member == null) {
            return null;
        }
        if (member.getPassword().equals(loginForm.getPasswd())) {
            return member;
        } else {
            return null;
        }
    }


}
