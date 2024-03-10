package fel.cvut.cz.dao;

import fel.cvut.cz.entity.CustomerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class CustomerDAO extends DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDAO(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    @Transactional
    public CustomerEntity read(String nationalId) {
        return entityManager.find(CustomerEntity.class, nationalId);
    }


    @Transactional
    public void delete(String nationalId) {
        CustomerEntity person = entityManager.find(CustomerEntity.class, nationalId);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    @Transactional
    public List<CustomerEntity> getAll() {
        return entityManager.createQuery("SELECT p FROM CustomerEntity p", CustomerEntity.class)
                .getResultList();
    }
}
