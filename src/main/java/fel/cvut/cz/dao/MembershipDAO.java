package fel.cvut.cz.dao;

import fel.cvut.cz.entity.MembershipEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class MembershipDAO extends DAO {

    @PersistenceContext
    private EntityManager em;

    public MembershipDAO(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Transactional
    public MembershipEntity read(String type) {
        return em.find(MembershipEntity.class, type);
    }


    @Transactional
    public void delete(String i) {
        MembershipEntity m = em.find(MembershipEntity.class, i);
        if (m != null) {
            em.remove(m);
        }
    }

    @Transactional
    public List<MembershipEntity> getAll() {
        return em.createQuery("SELECT p FROM MembershipEntity p", MembershipEntity.class)
                .getResultList();
    }
}
