package ru.alvion.data.jpa.repository;

import org.springframework.stereotype.Repository;
import ru.alvion.data.jpa.baserepos.GenericRepository;
import ru.alvion.data.jpa.domain.BigRabbit;

@Repository
public interface BigRabbitRepository extends GenericRepository<BigRabbit, Long> {

}
