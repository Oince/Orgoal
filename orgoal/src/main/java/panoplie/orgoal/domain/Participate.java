package panoplie.orgoal.domain;

import java.util.Date;

public class Participate {
    private int aid;
    private int mid;
    private Date applicationDate;
    private char state;
    private char changed;
    private String answer;

    public Participate() {}

    public Participate(int aid, int mid, Date applicationDate, char state, char changed, String answer) {
        this.aid = aid;
        this.mid = mid;
        this.applicationDate = applicationDate;
        this.state = state;
        this.changed = changed;
        this.answer = answer;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public char getChanged() {
        return changed;
    }

    public void setChanged(char changed) {
        this.changed = changed;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Participate{" +
                "aid=" + aid +
                ", mid=" + mid +
                ", applicationDate=" + applicationDate +
                ", state=" + state +
                ", changed=" + changed +
                ", answer='" + answer + '\'' +
                '}';
    }
}
