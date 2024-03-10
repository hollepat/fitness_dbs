package fel.cvut.cz.dao;

import fel.cvut.cz.entity.InvoiceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class InvoiceDAO extends DAO {

    @PersistenceContext
    EntityManager em;

    public InvoiceDAO(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Transactional
    public InvoiceEntity read(Integer i) {
        return em.find(InvoiceEntity.class, i);
    }


    @Transactional
    public void delete(Integer i) {
        InvoiceEntity invoice = em.find(InvoiceEntity.class, i);
        if (invoice != null) {
            em.remove(invoice);
        }
    }

    @Transactional
    public List<InvoiceEntity> getAll() {
        return em.createQuery("SELECT p FROM InvoiceEntity p", InvoiceEntity.class)
                .getResultList();
    }
}
