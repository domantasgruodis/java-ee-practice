package lt.vu.persistence;

import lt.vu.entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourseDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Course course) {
        em.persist(course);
    }

    public Course loadOne(Integer id) {
        return em.find(Course.class, id);
    }

    public List<Course> loadAll() {
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    public List<Course> loadAllByStudyProgrammeId(Integer studyProgrammeId) {
        return em.createNamedQuery("Course.findAllByStudyProgrammeId", Course.class)
                .setParameter("studyProgrammeId", studyProgrammeId).getResultList();
    }
    public Course update(Course course) {
        return em.merge(course);
    }
}
