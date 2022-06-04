package panoplie.orgoal.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    //hky0105@naver.com 에 대한 토큰
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTMwNjQzMzAsImVtYWlsIjoiaGt5MDEwNUBuYXZlci5jb20ifQ.5h4UsH0eLivVU1Ga6XU5QwIS5r2ejS9QuYlMqyU3am4

    @Test
    @DisplayName("토큰 생성 & 파싱")
    void createToken() {
        String email = "hky0105@naver.com";

        String token = JwtTokenProvider.createToken(email, 21);
        UserClaim userClaim = JwtTokenProvider.getUserClaim(token);

        assertEquals(email, userClaim.getEmail());
        assertEquals(21, userClaim.getMid());

    }

//    @Test
//    @DisplayName("토큰 검증")
//    void getUserClaim() {
//
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTMwNjQzMzAsImVtYWlsIjoiaGt5MDEwNUBuYXZlci5jb20ifQ.5h4UsH0eLivVU1Ga6XU5QwIS5r2ejS9QuYlMqyU3am4";
//
//        assertDoesNotThrow(() -> {
//            JwtTokenProvider.getUserClaim(token);
//        });
//
//    }
}