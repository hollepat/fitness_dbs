package fel.cvut.cz.dao;

import fel.cvut.cz.entity.TrainerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class TrainerDAO extends DAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TrainerDAO(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    @Transactional
    public TrainerEntity read(String nationalId) {
        return entityManager.find(TrainerEntity.class, nationalId);
    }


    @Transactional
    public void delete(String nationalId) {
        TrainerEntity person = entityManager.find(TrainerEntity.class, nationalId);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    @Transactional
    public List<TrainerEntity> getAll() {
        return entityManager.createQuery("SELECT p FROM TrainerEntity p", TrainerEntity.class)
                .getResultList();
    }
}
