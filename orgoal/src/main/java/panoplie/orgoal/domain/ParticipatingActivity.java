package panoplie.orgoal.domain;

public class ParticipatingActivity {

    private int aid;
    private String title;
    private char state;

    public ParticipatingActivity() {}

    public ParticipatingActivity(int aid, String title, char state) {
        this.aid = aid;
        this.title = title;
        this.state = state;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
}
