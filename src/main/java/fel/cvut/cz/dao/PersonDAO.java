package fel.cvut.cz.dao;

import fel.cvut.cz.entity.PersonEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class PersonDAO extends DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDAO(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    @Transactional
    public PersonEntity read(String nationalId) {
        return entityManager.find(PersonEntity.class, nationalId);
    }


    @Transactional
    public void delete(String nationalId) {
        PersonEntity person = entityManager.find(PersonEntity.class, nationalId);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    @Transactional
    public List<PersonEntity> getAll() {
        return entityManager.createQuery("SELECT p FROM PersonEntity p", PersonEntity.class)
                .getResultList();
    }
}
