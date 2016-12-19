package pl.edu.agh.to.mosti.comparator;

import pl.edu.agh.to.mosti.comparator.model.Section;
import javax.persistence.*;
import java.util.List;

final class RdbSectionDao extends SectionDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    protected List<Section> getAll() {
        entityManager.getTransaction().begin();
        List<Section> result = entityManager.createQuery("SELECT s FROM Section s", Section.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    protected Section getById(long id) {
        entityManager.getTransaction().begin();
        Section result = entityManager.createQuery("SELECT s FROM Section s WHERE s.id LIKE :idnum ", Section.class).setParameter("idnum", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    protected void save(Section section) {
        entityManager.getTransaction().begin();
        entityManager.persist(section);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected void update(Section section) {
        entityManager.getTransaction().begin();
        Section result = entityManager.createQuery("SELECT s FROM Section s WHERE s LIKE :section ", Section.class).setParameter("section", section).getSingleResult();
        if (result != null)
        {
            this.save(section);
        }
        else
        {}
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    protected void delete(Section section) {
        entityManager.getTransaction().begin();
        entityManager.remove(section);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
