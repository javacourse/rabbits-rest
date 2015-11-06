package ru.alvion.data.jpa.web.dto;

/**
 *
 */
public class RabbitMotionEventDto {

    private long id;

    private String motionStart;

    private String motionEnd;

    private int distance;

    private long rabbitId;

    public RabbitMotionEventDto() {
    }

    public RabbitMotionEventDto(int distance, long id, String motionEnd, String motionStart, long rabbitId) {
        this.distance = distance;
        this.id = id;
        this.motionEnd = motionEnd;
        this.motionStart = motionStart;
        this.rabbitId = rabbitId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

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

    public long getRabbitId() {
        return rabbitId;
    }

    public void setRabbitId(long rabbitId) {
        this.rabbitId = rabbitId;
    }
}
