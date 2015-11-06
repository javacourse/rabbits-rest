package ru.alvion.data.jpa.baserepos;

import com.mysema.query.dml.DeleteClause;
import com.mysema.query.dml.UpdateClause;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.EntityPath;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

@SuppressWarnings("unchecked")
@NoRepositoryBean
@Transactional
public class GenericRepositoryImpl<T, ID extends Serializable>
        extends QueryDslJpaRepository<T, ID> implements GenericRepository<T, ID>, Serializable {

    private EntityManager entityManager;

    // There are two constructors to choose from, either can be used.
    /*public GenericRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }*/
    public GenericRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
        this.entityManager = entityManager;
    }

    public T update(T entity) {
        T updated = entityManager.merge(entity);
        return updated;
    }

    @Override
    public UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
        return new JPAUpdateClause(entityManager, path);
    }

    @Override
    public DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
        return new JPADeleteClause(entityManager, path);
    }

    @Override
    @Transactional(readOnly = true)
    public JPQLQuery from(EntityPath<?>... paths) {
        return new JPAQuery(entityManager, HQLTemplates.DEFAULT).from(paths);
    }
}
