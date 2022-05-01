package panoplie.orgoal.domain;

public class SignUpForm {
    private String email;
    private String nickname;
    private String passwd;
    private PasswdQuestion question;
    private String answer;

    public SignUpForm(String email, String nickname, String passwd, PasswdQuestion question, String answer) {
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
        this.question = question;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public PasswdQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PasswdQuestion question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
