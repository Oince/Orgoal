package panoplie.orgoal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {
    private final static String secretKey = "orgoalsecretkey";

    public static String createToken(String email, int mid) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date())
                .claim("email", email)
                .claim("mid", mid)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private static Claims parsingToken(String token) {
        return  Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static UserClaim getUserClaim(String token) {

        Claims claims = parsingToken(token);

        return new UserClaim(claims);
    }

}
