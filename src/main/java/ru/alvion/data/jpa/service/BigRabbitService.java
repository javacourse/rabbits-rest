package ru.alvion.data.jpa.service;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.web.dto.RabbitMotionEventDto;
import ru.alvion.data.jpa.web.dto.RabbitPastureEventDto;

import java.util.List;

public interface BigRabbitService {

    Page<BigRabbit> findRabbits(RabbitSearchCriteria criteria, Pageable pageable);

    List<BigRabbit> findAllRabbits();

    BigRabbit getRabbit(long id);

    void killRabbit(long id);

    BigRabbit updateRabbit(BigRabbit rabbit);

    BigRabbit createRabbit(BigRabbit rabbit);

    Iterable<RabbitMotionEventDto> listRabbitMotions(long rabbitId);

    Iterable<RabbitPastureEventDto> listRabbitPastures(long rabbitId);


    /**
     * Перемещение кролика великана на N километров при моционе за указанный период
     *
     * @param dto
     * @return
     */
    RabbitMotionEventDto createRabbitMotionEvent(RabbitMotionEventDto dto);

    /**
     * Пастба кролика великана на пастбище в указанный период (допустимые варианты пастбищ: хорошее и
     * неудовлетворительное);
     *
     * @param dto
     * @return
     */
    RabbitPastureEventDto createRabbitPastureEvent(RabbitPastureEventDto dto);

    /**
     * Определение потреблённой энергии кроликом великаном за указанный период
     *
     * @param fromDate начало периода
     * @param toDate   конец периода
     * @return Количество энергии Дж
     */
    String calculateRabbitEnergy(Long rabbitId, DateTime fromDate, DateTime toDate);

}
