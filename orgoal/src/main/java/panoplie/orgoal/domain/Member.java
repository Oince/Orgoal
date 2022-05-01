package panoplie.orgoal.domain;

public class Member {

    private long id;
    private String email;
    private String nickname;
    private String passwd;
    private PasswdQuestion question;
    private String answer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member(SignUpForm signUpForm) {
        email = signUpForm.getEmail();
        nickname = signUpForm.getNickname();
        passwd = signUpForm.getPasswd();
        question = signUpForm.getQuestion();
        answer = signUpForm.getAnswer();
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
