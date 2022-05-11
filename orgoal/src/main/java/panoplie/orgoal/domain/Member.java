package panoplie.orgoal.domain;

public class Member {

    private int mid;
    private String email;
    private String password;
    private String nickname;
    private String question;
    private String answer;

    public Member() {}

    public Member(SignUpForm signUpForm) {
        this(signUpForm.getEmail(), signUpForm.getPassword(), signUpForm.getNickname(), signUpForm.getQuestion(), signUpForm.getAnswer());
    }

    public Member(String email, String password, String nickname, String question, String answer) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.question = question;
        this.answer = answer;
    }

    public Member(int mid, String email, String password, String nickname, String question, String answer) {
        this.mid = mid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Member{" +
                "mid='" + mid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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





