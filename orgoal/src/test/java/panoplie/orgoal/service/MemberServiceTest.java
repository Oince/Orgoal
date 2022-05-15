package panoplie.orgoal.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import panoplie.orgoal.domain.LoginForm;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.domain.SignUpForm;
import panoplie.orgoal.repository.MemoryMemberRepository;
import panoplie.orgoal.security.SHA256;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원 가입")
    void signUp() throws NoSuchAlgorithmException, DuplicateMemberException {
        SignUpForm member1 = new SignUpForm("hky0105@nave.com", "asdfzxcv123", "Oince", "PETNAME", "다온");

        memberService.signUp(member1);

        Member member2 = memberRepository.findByEmail(member1.getEmail());
        assertEquals(member1.getEmail(), member2.getEmail());

    }

//    @Test
//    @DisplayName("로그인")
//    void login() throws NoSuchAlgorithmException, DuplicateMemberException, NotFoundException {
//        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
//        memberService.signUp(member1);
//        LoginForm loginForm = new LoginForm("hky0105@naver.com", "asdfzxcv123");
//
//        Member member2 = memberService.signIn(loginForm);
//
//        assertEquals(member2.getEmail(), member1.getEmail());
//    }

    @Test
    @DisplayName("비밀번호 해시")
    void passwordHash() throws NoSuchAlgorithmException, DuplicateMemberException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member1);

        String encrypt = SHA256.encrypt(member1.getPassword());
        Member member2 = memberRepository.findByEmail(member1.getEmail());

        assertEquals(encrypt, member2.getPassword());

    }
}