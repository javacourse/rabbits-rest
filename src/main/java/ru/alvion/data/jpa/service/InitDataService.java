package ru.alvion.data.jpa.service;

import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.domain.PastureType;
import ru.alvion.data.jpa.domain.RabbitMotionEvent;
import ru.alvion.data.jpa.domain.RabbitPastureEvent;
import ru.alvion.data.jpa.repository.BigRabbitRepository;
import ru.alvion.data.jpa.repository.RabbitMotionEventRepository;
import ru.alvion.data.jpa.repository.RabbitPastureEventRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@Transactional
/**
 * Test data generator
 */
public class InitDataService {

    @Autowired
    private BigRabbitService bigRabbitService;
    @Autowired
    private BigRabbitRepository bigRabbitRepository;
    @Autowired
    private RabbitMotionEventRepository rabbitMotionEventRepository;
    @Autowired
    private RabbitPastureEventRepository rabbitPastureEventRepository;


    @PostConstruct
    public void init() {
        int rabbitCount = 10;
        String colors[] = {"black", "white", "grey"};
        PastureType pastureTypes[] = {PastureType.GOOD, PastureType.BAD};
        List<BigRabbit> rabbits = new ArrayList<>();
        List<RabbitMotionEvent> motionEvents = new ArrayList<>();
        List<RabbitPastureEvent> pastureEvents = new ArrayList<>();
        for (int i = 1; i <= rabbitCount; i++) {
            BigRabbit rabbit = new BigRabbit();
            rabbit.setName("Rabbit_" + i);
            rabbit.setAge(RandomUtils.nextInt(1, 5));
            rabbit.setMass(RandomUtils.nextInt(1, 5));
            rabbit.setColor(colors[RandomUtils.nextInt(0, 3)]);
            rabbit.setPhotoUrl("/" + rabbit.getName());
            rabbits.add(rabbit);

            // ??????? ??????????? ????????
            int motionCount = 3;
            for (int j = 1; j <= motionCount; j++) {
                RabbitMotionEvent motionEvent = new RabbitMotionEvent();
                motionEvent.setDistance(RandomUtils.nextInt(1, 5));
                DateTime motionStart = new DateTime().minusDays(6 - j).
                        withTime(10 + j * 2, 1, 1, 1);
                DateTime motionEnd = new DateTime().minusDays(5 - j).
                        withTime(11 + j, 1, 1, 1);
                motionEvent.setMotionStart(motionStart);
                motionEvent.setMotionEnd(motionEnd);
                motionEvent.setRabbit(rabbit);

                motionEvents.add(motionEvent);
            }

            // ??????? ?????? ????????
            int pastureCount = 3;
            for (int j = 1; j <= pastureCount; j++) {
                RabbitPastureEvent pastureEvent = new RabbitPastureEvent();
                pastureEvent.setPastureType(pastureTypes[RandomUtils.nextInt(0, 2)]);
                DateTime motionStart = new DateTime().minusDays(4 - j).
                        withTime(10 + j * 2, 1, 1, 1);
                DateTime motionEnd = new DateTime().minusDays(3 - j).
                        withTime(11 + j, 1, 1, 1);
                pastureEvent.setMotionStart(motionStart);
                pastureEvent.setMotionEnd(motionEnd);
                pastureEvent.setRabbit(rabbit);

                pastureEvents.add(pastureEvent);
            }
        }

        bigRabbitRepository.save(rabbits);
        rabbitMotionEventRepository.save(motionEvents);
        rabbitPastureEventRepository.save(pastureEvents);

        System.out.println("InitDataService");
    }
}
