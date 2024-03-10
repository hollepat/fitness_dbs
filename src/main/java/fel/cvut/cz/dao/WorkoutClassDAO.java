package fel.cvut.cz.dao;

import fel.cvut.cz.entity.WorkoutclassEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class WorkoutClassDAO extends DAO {

    @PersistenceContext
    EntityManager em;

    public WorkoutClassDAO(EntityManager em) {
        super(em);
        this.em = em;
    }

    public WorkoutclassEntity read(int id) {
        return em.find(WorkoutclassEntity.class, id);
    }

    @Transactional
    public void delete(String id) {
        WorkoutclassEntity w = em.find(WorkoutclassEntity.class, id);
        if (w != null) {
            em.remove(w);
        }
    }

    @Transactional
    public List<WorkoutclassEntity> getAll() {
        return em.createQuery("SELECT p FROM WorkoutclassEntity p", WorkoutclassEntity.class)
                .getResultList();
    }
}
