package panoplie.orgoal.todo;

public class Todo {

    private int id;
    private String text;
    private boolean isFinished;

    public Todo() {}

    public Todo(int id, String text, boolean isFinished) {
        this.id = id;
        this.text = text;
        this.isFinished = isFinished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
