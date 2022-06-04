package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.repository.MemberRepository;
import panoplie.orgoal.security.JwtTokenProvider;
import panoplie.orgoal.security.SHA256;

import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(SignUpForm signUpForm) throws DuplicateMemberException, NoSuchAlgorithmException {
        //회원가입 폼으로 member 객체 생성
        Member member = new Member(signUpForm);

        //이미 존재하는 회원이라면 예외 발생
        if (isExist(member.getEmail())) {
            throw new DuplicateMemberException("Duplicated");
        }

        //비밀번호를 SHA-256으로 해시해서 member객체에 저장
        String encryptPassword = SHA256.encrypt(member.getPassword());
        member.setPassword(encryptPassword);

        //DB에 member 저장
        memberRepository.save(member);
    }

    //중복된 회원인지를 판단하는 메소드
    public boolean isExist(String email) {
        Member byEmail = memberRepository.findByEmail(email);
        return byEmail != null;
    }

    public String signIn(LoginForm loginForm) throws NoSuchAlgorithmException, NotFoundException, IllegalStateException {

        //email로 찾아서 member 가져옴
        Member member = memberRepository.findByEmail(loginForm.getEmail());
        //null이면 member가 존재하지 않는 것, 예외 던짐
        if (member == null) {
            throw new NotFoundException("Not exist");
        }

        //로그인 폼의 비밀번호를 해시해서 찾은 member의 비밀번호와 대조
        String encryptPassword = SHA256.encrypt(loginForm.getPassword());
        loginForm.setPassword(encryptPassword);

        //같으면 member 리턴, 다르면 예외 던짐
        if (member.getPassword().equals(loginForm.getPassword())) {
            return JwtTokenProvider.createToken(member.getEmail(), member.getMid());
        } else {
            throw new IllegalStateException("Password not match");
        }
    }

    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member getMember(int mid) {
        return memberRepository.findById(mid);
    }

}
