package lt.vu.jpa.persistence;

import lt.vu.jpa.entities.Faculty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FacultyDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Faculty faculty) {
        em.persist(faculty);
    }

    public Faculty loadOne(Integer id) {
        return em.find(Faculty.class, id);
    }

    public List<Faculty> loadAll() {
        return em.createNamedQuery("Faculty.findAll", Faculty.class).getResultList();
    }

    public Faculty update(Faculty faculty) {
        return em.merge(faculty);
    }
}
