package panoplie.orgoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.repository.MemberRepository;

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
        member.setPassword(member.getPassword().trim());
        if (member.getPassword().equals(loginForm.getPassword())) {
            return member;
        } else {
            return null;
        }
    }


}
