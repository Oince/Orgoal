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
import panoplie.orgoal.security.JwtTokenProvider;
import panoplie.orgoal.security.SHA256;
import panoplie.orgoal.security.UserClaim;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        MemoryMemberRepository.clearStore();
    }

    @Test
    @DisplayName("회원 가입")
    void signUp() throws NoSuchAlgorithmException, DuplicateMemberException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");

        memberService.signUp(member1);

        Member member2 = memberRepository.findByEmail(member1.getEmail());
        assertEquals(member1.getEmail(), member2.getEmail());
    }

    @Test
    @DisplayName("중복 회원 가입")
    void duplicatedMember() {
        Member member1 = new Member(1, "hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberRepository.save(member1);

        SignUpForm member2 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");

        assertThrows(DuplicateMemberException.class, () ->{
            memberService.signUp(member2);
        });
    }

    @Test
    @DisplayName("로그인")
    void login() throws DuplicateMemberException, NoSuchAlgorithmException, NotFoundException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member1);
        LoginForm loginForm = new LoginForm("hky0105@naver.com", "asdfzxcv123");

        String token = memberService.signIn(loginForm);
        UserClaim userClaim = JwtTokenProvider.getUserClaim(token);
        String email = userClaim.getEmail();

        assertEquals(member1.getEmail(), email);
    }

    @Test
    @DisplayName("로그인 - 비밀번호 매치 실패")
    void passwordNotMatch() throws DuplicateMemberException, NoSuchAlgorithmException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member1);

        LoginForm loginForm = new LoginForm("hky0105@naver.com", "asdfzxcv");

        assertThrows(IllegalStateException.class, () -> {
            memberService.signIn(loginForm);
        });
    }

    @Test
    @DisplayName("로그인 - 아이디 없음")
    void notExist() throws DuplicateMemberException, NoSuchAlgorithmException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member1);

        LoginForm loginForm = new LoginForm("hky0105", "asdfzxcv123");

        assertThrows(NotFoundException.class, () -> {
            memberService.signIn(loginForm);
        });
    }

    @Test
    @DisplayName("비밀번호 해시")
    void passwordHash() throws NoSuchAlgorithmException, DuplicateMemberException {
        SignUpForm member1 = new SignUpForm("hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온");
        memberService.signUp(member1);

        String encrypt = SHA256.encrypt(member1.getPassword());
        Member member2 = memberService.getMember(member1.getEmail());

        assertEquals(encrypt, member2.getPassword());
    }
}