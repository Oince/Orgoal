package panoplie.orgoal.security;

import io.jsonwebtoken.Claims;

public class UserClaim {

    private final String email;

    public UserClaim(Claims claims) {
        this.email = claims.get("email", String.class);
    }

    public String getEmail() {
        return email;
    }
}
