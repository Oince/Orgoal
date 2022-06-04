package panoplie.orgoal.domain;

public class Applicant {

    private String email;
    private String nickname;
    private String answer;

    public Applicant() {}

    public Applicant(String email, String nickname, String answer) {
        this.email = email;
        this.nickname = nickname;
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
