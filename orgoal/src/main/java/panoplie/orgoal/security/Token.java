package panoplie.orgoal.security;

public class Token{
    private final String token;
    private String test;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
