package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.SectionSnapshot;
import javax.persistence.*;
import java.util.List;

final class RdbSectionSnapshotDao extends SectionSnapshotDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    protected List<SectionSnapshot> getAllBySectionId(long id) {
        entityManager.getTransaction().begin();
        List<SectionSnapshot> result = entityManager.createQuery("SELECT s FROM SectionSnapshot s WHERE s.session.id LIKE :idnum ",
                SectionSnapshot.class).setParameter("idnum", id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    protected SectionSnapshot getLatestBySectionId(long id) {
        entityManager.getTransaction().begin();
        SectionSnapshot result = entityManager.createQuery("SELECT s FROM SectionSnapshot s WHERE s.session.id LIKE :idnum ORDER BY s.id DESC",
                SectionSnapshot.class).setParameter("idnum", id).setFirstResult(0).setMaxResults(1).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    protected void save(SectionSnapshot sectionSnapshot) {
        entityManager.getTransaction().begin();
        entityManager.persist(sectionSnapshot);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected void update(SectionSnapshot sectionSnapshot) {
        entityManager.getTransaction().begin();
        SectionSnapshot result = entityManager.createQuery("SELECT s FROM SectionSnapshot s WHERE s LIKE :sectionSnapshot ", SectionSnapshot.class).setParameter("sectionSnapshot", sectionSnapshot).getSingleResult();
        if (result != null)
        {
            this.save(sectionSnapshot);
        }
        else
        {}
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected void delete(SectionSnapshot sectionSnapshot) {
        entityManager.getTransaction().begin();
        entityManager.remove(sectionSnapshot);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
