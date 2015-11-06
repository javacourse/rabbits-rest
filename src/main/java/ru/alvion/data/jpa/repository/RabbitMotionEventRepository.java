package ru.alvion.data.jpa.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.data.jpa.baserepos.GenericRepository;
import ru.alvion.data.jpa.domain.RabbitMotionEvent;

@Repository
public interface RabbitMotionEventRepository extends GenericRepository<RabbitMotionEvent, Long> {

}
