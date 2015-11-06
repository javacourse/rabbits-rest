package ru.alvion.data.jpa.service.mapper;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.domain.RabbitPastureEvent;
import ru.alvion.data.jpa.domain.TimeFormats;
import ru.alvion.data.jpa.repository.BigRabbitRepository;
import ru.alvion.data.jpa.web.dto.RabbitPastureEventDto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class RabbitPastureEventMapper {

    @Autowired
    private BigRabbitRepository rabbitRepository;

    public RabbitPastureEvent dtoToDomain(RabbitPastureEventDto dto) {

        DateTimeFormatter fmt = TimeFormats.DATE_FORMATTER;
        RabbitPastureEvent pastureEvent = new RabbitPastureEvent();
        pastureEvent.setMotionStart(fmt.parseDateTime(dto.getMotionStart()));
        pastureEvent.setMotionEnd(fmt.parseDateTime(dto.getMotionEnd()));
        pastureEvent.setPastureType(dto.getPastureType());

        BigRabbit rabbit = rabbitRepository.findOne(dto.getRabbitId());
        pastureEvent.setRabbit(rabbit);
        return pastureEvent;
    }

    public RabbitPastureEventDto domainToDto(RabbitPastureEvent domainObject) {
        RabbitPastureEventDto dto = new RabbitPastureEventDto();
        dto.setId(domainObject.getId());
        dto.setPastureType(domainObject.getPastureType());
        dto.setMotionStart(domainObject.getMotionStart().toString(TimeFormats.DD_MM_YYYY_HH_MM));
        dto.setMotionEnd(domainObject.getMotionEnd().toString(TimeFormats.DD_MM_YYYY_HH_MM));
        dto.setRabbitId(domainObject.getRabbit().getId());
        return dto;
    }

    public Iterable<RabbitPastureEventDto> domainToDto(Iterable<RabbitPastureEvent> domains) {
        List<RabbitPastureEventDto> dtos = new ArrayList<>();
        for (RabbitPastureEvent domain : domains) {
            dtos.add(domainToDto(domain));
        }
        return dtos;
    }
}
