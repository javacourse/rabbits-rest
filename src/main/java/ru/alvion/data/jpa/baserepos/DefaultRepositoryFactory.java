package ru.alvion.data.jpa.baserepos;
/**
 * The purpose of this class is to override the default behaviour of the spring JpaRepositoryFactory class.
 * It will produce a GenericRepositoryImpl object instead of SimpleJpaRepository.
 */

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class DefaultRepositoryFactory<S, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public DefaultRepositoryFactory(EntityManager entityManager) {
        super(entityManager);

        this.entityManager = entityManager;
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {

        //return new GenericRepositoryImpl<S, ID>((Class<S>) metadata.getDomainType(), entityManager);
        JpaEntityInformation<S, ID> entityInformation = (JpaEntityInformation<S, ID>) getEntityInformation(metadata.getDomainType());

        return new GenericRepositoryImpl<S, ID>(entityInformation, entityManager);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

        // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
        //to check for QueryDslJpaRepository's which is out of scope.
        return GenericRepositoryImpl.class;
    }
}
