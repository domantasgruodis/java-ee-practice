package lt.vu.persistence;

import lt.vu.entities.Lecturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LecturerDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Lecturer lecturer) {
        em.persist(lecturer);
    }

    public Lecturer loadOne(Integer id) {
        return em.find(Lecturer.class, id);
    }

    public List<Lecturer> loadAll() {
        return em.createNamedQuery("Lecturer.findAll", Lecturer.class).getResultList();
    }

    public Lecturer update(Lecturer lecturer) {
        return em.merge(lecturer);
    }
}
