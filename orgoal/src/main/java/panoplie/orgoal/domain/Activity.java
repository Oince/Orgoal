package panoplie.orgoal.domain;

import java.util.Date;

public class Activity {
    private int aid;
    private String title;
    private String content;
    private char state;
    private int mid;
    private Date lastModification;

    public Activity() {}

    public Activity(ActivityForm activityForm) {
        this.title = activityForm.getTitle();
        this.content = activityForm.getContent();
    }
    public Activity(String title, String content, char state, int mid, Date lastModification) {
        this.title = title;
        this.content = content;
        this.state = state;
        this.mid = mid;
        this.lastModification = lastModification;
    }

    public Activity(int aid, String title, String content, char state, int mid, Date lastModification) {
        this.aid = aid;
        this.title = title;
        this.content = content;
        this.state = state;
        this.mid = mid;
        this.lastModification = lastModification;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", mid=" + mid +
                ", lastModification=" + lastModification +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }
}
