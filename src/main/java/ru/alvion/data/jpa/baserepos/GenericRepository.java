package ru.alvion.data.jpa.baserepos;

import com.mysema.query.dml.DeleteClause;
import com.mysema.query.dml.UpdateClause;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.EntityPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 *
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

    T update(T entity);

    UpdateClause<JPAUpdateClause> update(EntityPath<?> path);

    DeleteClause<JPADeleteClause> delete(EntityPath<?> path);

    JPQLQuery from(EntityPath<?>... paths);
}
