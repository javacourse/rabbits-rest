package ru.alvion.data.jpa.web.dto;

import ru.alvion.data.jpa.domain.PastureType;

/**
 *
 */
public class RabbitPastureEventDto {

    private long id;

    private String motionStart;

    private String motionEnd;

    private PastureType pastureType;

    private long rabbitId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotionEnd() {
        return motionEnd;
    }

    public void setMotionEnd(String motionEnd) {
        this.motionEnd = motionEnd;
    }

    public String getMotionStart() {
        return motionStart;
    }

    public void setMotionStart(String motionStart) {
        this.motionStart = motionStart;
    }

    public PastureType getPastureType() {
        return pastureType;
    }

    public void setPastureType(PastureType pastureType) {
        this.pastureType = pastureType;
    }

    public long getRabbitId() {
        return rabbitId;
    }

    public void setRabbitId(long rabbitId) {
        this.rabbitId = rabbitId;
    }
}
