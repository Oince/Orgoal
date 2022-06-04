package panoplie.orgoal.controller;

import java.util.Date;

public class Message {
    private final int status;
    private final String message;
    private final Date time;

    public Message(int status, String message, Date time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Date getTime() {
        return time;
    }
}
