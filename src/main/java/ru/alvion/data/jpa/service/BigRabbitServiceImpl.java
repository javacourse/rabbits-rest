package ru.alvion.data.jpa.service;

import com.mysema.query.types.Predicate;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.alvion.data.jpa.domain.*;
import ru.alvion.data.jpa.repository.BigRabbitRepository;
import ru.alvion.data.jpa.repository.RabbitMotionEventRepository;
import ru.alvion.data.jpa.repository.RabbitPastureEventRepository;
import ru.alvion.data.jpa.service.exception.RabbitException;
import ru.alvion.data.jpa.service.mapper.RabbitMotionEventMapper;
import ru.alvion.data.jpa.service.mapper.RabbitPastureEventMapper;
import ru.alvion.data.jpa.web.dto.RabbitMotionEventDto;
import ru.alvion.data.jpa.web.dto.RabbitPastureEventDto;

import java.util.List;

/**
 *
 */
@Service("rabbitService")
@Transactional
public class BigRabbitServiceImpl implements BigRabbitService {

    @Autowired
    private BigRabbitRepository rabbitRepository;
    @Autowired
    private RabbitMotionEventRepository rabbitMotionEventRepository;
    @Autowired
    private RabbitMotionEventMapper rabbitMotionEventMapper;
    @Autowired
    private RabbitPastureEventRepository rabbitPastureEventRepository;
    @Autowired
    private RabbitPastureEventMapper rabbitPastureEventMapper;

    private QBigRabbit qRabbit = QBigRabbit.bigRabbit;
    private QRabbitMotionEvent qMotion = QRabbitMotionEvent.rabbitMotionEvent;
    private QRabbitPastureEvent qPasture = QRabbitPastureEvent.rabbitPastureEvent;


    @Override
    public Page<BigRabbit> findRabbits(RabbitSearchCriteria criteria, Pageable pageable) {
        Predicate predicate = qRabbit.name.eq(criteria.getName()).
                or(qRabbit.color.eq(criteria.getColor())).
                or(qRabbit.age.eq(criteria.getAge())).
                or(qRabbit.photoUrl.eq(criteria.getUrl()));
        return rabbitRepository.findAll(predicate, pageable);
    }

    @Override
    public List<BigRabbit> findAllRabbits() {
        return rabbitRepository.findAll();
    }

    @Override
    public BigRabbit getRabbit(long id) {
        BigRabbit rabbit = rabbitRepository.findOne(id);
        if (rabbit == null) {
            throw new RabbitException(String.format("Rabbit with id %d not found!", id));
        }
        return rabbit;
    }

    @Override
    public void killRabbit(long id) {
        //TODO delete constraints
        getRabbit(id);
        rabbitRepository.delete(id);
    }

    @Override
    public BigRabbit updateRabbit(BigRabbit rabbit) {
        Assert.notNull(rabbit);
        BigRabbit updated = rabbitRepository.update(rabbit);
        return updated;
    }

    @Override
    public BigRabbit createRabbit(BigRabbit rabbit) {
        Assert.notNull(rabbit);
        return rabbitRepository.save(rabbit);
    }

    @Override
    public Iterable<RabbitMotionEventDto> listRabbitMotions(long rabbitId) {
        BigRabbit rabbit = getRabbit(rabbitId);
        Predicate predicate = qMotion.rabbit.eq(rabbit);
        Iterable<RabbitMotionEvent> events = rabbitMotionEventRepository.findAll(predicate);
        Iterable<RabbitMotionEventDto> dtos = rabbitMotionEventMapper.domainToDto(events);
        return dtos;
    }

    @Override
    public Iterable<RabbitPastureEventDto> listRabbitPastures(long rabbitId) {
        BigRabbit rabbit = getRabbit(rabbitId);
        Predicate predicate = qPasture.rabbit.eq(rabbit);
        Iterable<RabbitPastureEvent> events = rabbitPastureEventRepository.findAll(predicate);
        Iterable<RabbitPastureEventDto> dtos = rabbitPastureEventMapper.domainToDto(events);
        return dtos;
    }

    @Override
    public RabbitMotionEventDto createRabbitMotionEvent(RabbitMotionEventDto dto) {
        Assert.notNull(dto);
        RabbitMotionEvent motionEvent = rabbitMotionEventRepository.save(rabbitMotionEventMapper.dtoToDomain(dto));

        return rabbitMotionEventMapper.domainToDto(motionEvent);
    }

    @Override
    public RabbitPastureEventDto createRabbitPastureEvent(RabbitPastureEventDto dto) {
        Assert.notNull(dto);
        RabbitPastureEvent motionEvent = rabbitPastureEventRepository.save(rabbitPastureEventMapper.dtoToDomain(dto));
        return rabbitPastureEventMapper.domainToDto(motionEvent);
    }

    @Override
    public String calculateRabbitEnergy(Long rabbitId, DateTime fromDate, DateTime toDate) {
        BigRabbit rabbit = getRabbit(rabbitId);
        int rabbitMass = rabbit.getMass();
        // ПЕ=88 х (масса тела) в степень 0,75
        //где ПЕ — поддерживающая энергия (энергия, необходимая для процессов жизнедеятельности в покое)
        double PE = 88 * Math.pow(rabbitMass, 0.75);
        double moveCoficient = 1.2; // Коеффициент движения

        Predicate motionPredicate = qMotion.rabbit.eq(rabbit).and(qMotion.motionStart.between(fromDate, toDate));
        Iterable<RabbitMotionEvent> motionEvents = rabbitMotionEventRepository.findAll(motionPredicate);

        long hours = 0;
        int distance = 0;
        for (RabbitMotionEvent motionEvent : motionEvents) {
            Duration duration = new Duration(motionEvent.getMotionStart(), motionEvent.getMotionEnd());
            hours += duration.getStandardHours();
            distance += motionEvent.getDistance();
        }


        Predicate predicate = qPasture.rabbit.eq(rabbit).and(qPasture.motionStart.between(fromDate, toDate));
        Iterable<RabbitPastureEvent> pastureEvents = rabbitPastureEventRepository.findAll(predicate);
        long pastureHours = 1;
        double pastureCoficient = 1;
        for (RabbitPastureEvent pastureEvent : pastureEvents) {
            Duration duration = new Duration(pastureEvent.getMotionStart(), pastureEvent.getMotionEnd());
            pastureHours += duration.getStandardHours();
            pastureCoficient += pastureEvent.getPastureType().getCoef();
        }
        // Секретная формула потреблённой энергии кроликом великаном
        double finalPE = PE * (hours / 24) * moveCoficient * distance * (pastureHours / 24) * pastureCoficient;
        return "" + Math.round(finalPE);
    }
}
