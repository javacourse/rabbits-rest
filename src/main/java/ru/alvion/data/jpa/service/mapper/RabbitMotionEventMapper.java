package ru.alvion.data.jpa.service.mapper;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.domain.RabbitMotionEvent;
import ru.alvion.data.jpa.domain.TimeFormats;
import ru.alvion.data.jpa.repository.BigRabbitRepository;
import ru.alvion.data.jpa.web.dto.RabbitMotionEventDto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class RabbitMotionEventMapper {

    @Autowired
    private BigRabbitRepository rabbitRepository;

    public RabbitMotionEvent dtoToDomain(RabbitMotionEventDto dto) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern(TimeFormats.DD_MM_YYYY_HH_MM);

        RabbitMotionEvent motionEvent = new RabbitMotionEvent();
        motionEvent.setMotionStart(fmt.parseDateTime(dto.getMotionStart()));
        motionEvent.setMotionEnd(fmt.parseDateTime(dto.getMotionEnd()));
        motionEvent.setDistance(dto.getDistance());

        BigRabbit rabbit = rabbitRepository.findOne(dto.getRabbitId());
        motionEvent.setRabbit(rabbit);
        return motionEvent;
    }

    public RabbitMotionEventDto domainToDto(RabbitMotionEvent domainObject) {
        RabbitMotionEventDto dto = new RabbitMotionEventDto();
        dto.setId(domainObject.getId());
        dto.setDistance(domainObject.getDistance());
        dto.setMotionStart(domainObject.getMotionStart().toString(TimeFormats.DD_MM_YYYY_HH_MM));
        dto.setMotionEnd(domainObject.getMotionEnd().toString(TimeFormats.DD_MM_YYYY_HH_MM));
        dto.setRabbitId(domainObject.getRabbit().getId());
        return dto;
    }

    public Iterable<RabbitMotionEventDto> domainToDto(Iterable<RabbitMotionEvent> domains) {
        List<RabbitMotionEventDto> dtos = new ArrayList<>();
        for (RabbitMotionEvent domain : domains) {
            dtos.add(domainToDto(domain));
        }
        return dtos;
    }
}
