package ru.alvion.data.jpa.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.EAGER;

@Entity
public class RabbitPastureEvent implements Serializable {

    private static final long serialVersionUID = 7478028219671167309L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = TimeFormats.DD_MM_YYYY_HH_MM)
    private DateTime motionStart;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = TimeFormats.DD_MM_YYYY_HH_MM)
    private DateTime motionEnd;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PastureType pastureType;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "rabbit_id")
    private BigRabbit rabbit;

    public RabbitPastureEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getMotionEnd() {
        return motionEnd;
    }

    public void setMotionEnd(DateTime motionEnd) {
        this.motionEnd = motionEnd;
    }

    public DateTime getMotionStart() {
        return motionStart;
    }

    public void setMotionStart(DateTime motionStart) {
        this.motionStart = motionStart;
    }

    public PastureType getPastureType() {
        return pastureType;
    }

    public void setPastureType(PastureType pastureType) {
        this.pastureType = pastureType;
    }

    public BigRabbit getRabbit() {
        return rabbit;
    }

    public void setRabbit(BigRabbit rabbit) {
        this.rabbit = rabbit;
    }
}
