package ru.alvion.data.jpa.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.data.jpa.baserepos.GenericRepository;
import ru.alvion.data.jpa.domain.RabbitPastureEvent;

@Repository
public interface RabbitPastureEventRepository extends GenericRepository<RabbitPastureEvent, Long> {

}
