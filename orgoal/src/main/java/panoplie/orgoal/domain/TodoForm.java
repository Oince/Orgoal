package panoplie.orgoal.domain;

//Post 요청에 사용할 form, text만 있음 나머지는 서버에서 설정
public class TodoForm {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
