/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.alvion.data.jpa.service;

import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.data.jpa.SampleDataJpaApplication;
import ru.alvion.data.jpa.domain.BigRabbit;
import ru.alvion.data.jpa.domain.QBigRabbit;
import ru.alvion.data.jpa.repository.BigRabbitRepository;

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Integration tests for {@link ru.alvion.data.jpa.repository.BigRabbitRepository}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
public class BigRabbitRepositoryIntegrationTests {

    @Autowired
    private BigRabbitRepository repository;

    private QBigRabbit bigRabbit = QBigRabbit.bigRabbit;

    @Test
    @Transactional
    public void findsFirstPageOfCities() {

        Page<BigRabbit> rabbits = repository.findAll(new PageRequest(0, 10));
        assertThat(rabbits.getTotalElements(), is(greaterThan(3L)));
    }

    @Test
    @Transactional
    public void testUpdateBigRabbit() {
        Predicate expression = bigRabbit.name.eq("Rabbit_1");
        //Predicate expression = bigRabbit.name.eq(new JPASubQuery().from(bigRabbit));
        BigRabbit bigRabbit1 = repository.findOne(expression);
        assertNotNull(bigRabbit1);
        assertEquals(bigRabbit1.getName(), "Rabbit_1");


        repository.update(bigRabbit).where(expression).set(bigRabbit.name, "Rabbit_1x").execute();

        BigRabbit bigRabbit2 = repository.findOne(expression);
        assertNull(bigRabbit2);

    }

    @Test(expected = PersistenceException.class)
    @Transactional

    public void testDeleteBigRabbit() {
        Predicate expression = bigRabbit.name.eq("Rabbit_1");
        BigRabbit bigRabbit1 = repository.findOne(expression);
        assertNotNull(bigRabbit1);
        assertEquals(bigRabbit1.getName(), "Rabbit_1");


        // Should fail
        repository.delete(bigRabbit).where(expression).execute();

        BigRabbit bigRabbit2 = repository.findOne(expression);
        assertNull(bigRabbit2);
    }
}
