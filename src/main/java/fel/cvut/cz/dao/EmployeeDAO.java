package fel.cvut.cz.dao;

import fel.cvut.cz.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class EmployeeDAO extends DAO {

    @PersistenceContext
    public EntityManager em;

    public EmployeeDAO(EntityManager em) {
        super(em);
        this.em = em;
    }

   @Transactional
    public EmployeeEntity read(String nationalId) {
        return em.find(EmployeeEntity.class, nationalId);
    }


    @Transactional
    public void delete(String nationalId) {
        EmployeeEntity person = em.find(EmployeeEntity.class, nationalId);
        if (person != null) {
            em.remove(person);
        }
    }

    @Transactional
    public List<EmployeeEntity> getAll() {
        return em.createQuery("SELECT p FROM EmployeeEntity p", EmployeeEntity.class)
                .getResultList();
    }
}
