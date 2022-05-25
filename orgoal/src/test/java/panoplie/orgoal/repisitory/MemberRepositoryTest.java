package panoplie.orgoal.repisitory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import panoplie.orgoal.domain.Member;
import panoplie.orgoal.repository.MemberRepository;
import panoplie.orgoal.security.SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    /* 더미 데이터 (21, "hky0105@naver.com", "asdfzxcv123", "Oince", "PETNAME", "다온") */

    @Test
    @DisplayName("전제 회원 가져오기")
    void getMember() {
        List<Member> members = memberRepository.getMembers();

        assertEquals(21, members.get(0).getMid());
    }

    @Test
    @DisplayName("회원 찾기")
    void findMember() {
        String email = "hky0105@naver.com";
        Member byEmail = memberRepository.findByEmail(email);
        assertEquals(email, byEmail.getEmail());

    }

    @Test
    @DisplayName("비밀번호 일치")
    void passwordMatch() throws NoSuchAlgorithmException {
        String email = "hky0105@naver.com";
        String password = "asdfzxcv123";

        Member byEmail = memberRepository.findByEmail(email);

        String encrypt = SHA256.encrypt(password);

        assertEquals(encrypt, byEmail.getPassword());
    }

    @Test
    @DisplayName("회원 저장 & 회원 찾기")
    void saveMember() {

        Member member = new Member("가나다", "바보", "asdf1234", "PETNAME", "멍멍이");
        memberRepository.save(member);
        Member findId = memberRepository.findByEmail(member.getEmail());
        assertEquals(member.getEmail(), findId.getEmail());
    }

}