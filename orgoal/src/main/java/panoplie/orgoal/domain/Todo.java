package panoplie.orgoal.domain;

//저장할 Todo 객체
public class Todo {

    private Long id;
    private String text;
    private boolean isFinished;

    public Todo() {}

    public Todo(String text, boolean isFinished) {
        this.text = text;
        this.isFinished = isFinished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
