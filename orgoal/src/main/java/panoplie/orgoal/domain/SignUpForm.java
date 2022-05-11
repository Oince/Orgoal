package panoplie.orgoal.domain;

public class SignUpForm {

    private String email;
    private String password;
    private String nickname;
    private String question;
    private String answer;

    public SignUpForm() {}
    public SignUpForm(String email, String password, String nickname, String question, String answer) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.question = question;
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
