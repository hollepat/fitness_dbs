package fel.cvut.cz.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public DAO(EntityManager em) {
        this.entityManager = em;
    }

    @Transactional
    public void create(Object o) {
        entityManager.persist(o);
    }


    @Transactional
    public void update(Object o) {
        entityManager.merge(o);
    }

    @Transactional
    public void delete(Object o) {
        entityManager.remove(o);

    }

}

