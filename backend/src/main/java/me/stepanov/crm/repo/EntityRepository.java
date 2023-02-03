package me.stepanov.crm.repo;

import lombok.extern.slf4j.Slf4j;
import me.stepanov.crm.domain.BaseEntity;
import me.stepanov.crm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Repository
public class EntityRepository {
    @Autowired
    private EntityManager entityManager;

    public <E extends BaseEntity> void persist(E entity) {
        entityManager.persist(entity);
    }

    public <E extends BaseEntity> void delete(E entity) {
        entityManager.remove(entity);
    }

    public <E extends BaseEntity> E getById(Class<E> entityClass, Long id) {
        return entityManager.find(entityClass, id);
    }

    public <E extends BaseEntity> List<E> listForUser(Class<E> entityClass, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = builder.createQuery(entityClass);
        Root<E> entity = cq.from(entityClass);
        Predicate authorNamePredicate = builder.equal(entity.get("user"), user);
        cq.where(authorNamePredicate);

        TypedQuery<E> query = entityManager.createQuery(cq);
        return query.getResultList();
    }


    public <E extends BaseEntity> List<E> list(Class<E> entityClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = builder.createQuery(entityClass);
        Root<E> entity = cq.from(entityClass);
        TypedQuery<E> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

}


