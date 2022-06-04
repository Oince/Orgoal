package panoplie.orgoal.security;

import io.jsonwebtoken.Claims;

public class UserClaim {

    private final String email;
    private final int mid;

    public UserClaim(Claims claims) {
        this.email = claims.get("email", String.class);
        this.mid = claims.get("mid", Integer.class);
    }

    public String getEmail() {
        return email;
    }

    public int getMid() {
        return mid;
    }
}
