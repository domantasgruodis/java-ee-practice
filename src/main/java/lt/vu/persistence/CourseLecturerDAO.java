package lt.vu.persistence;

import lt.vu.entities.Course;
import lt.vu.entities.CourseLecturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CourseLecturerDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(CourseLecturer courseLecturer) {
        em.persist(courseLecturer);
    }

    public List<CourseLecturer> loadByCourseId(Integer courseId) {
        return em.createNamedQuery("CourseLecturer.findAllByCourseId", CourseLecturer.class)
                .setParameter("courseId", courseId).getResultList();
    }

    public void remove(CourseLecturer courseLecturer) {
        em.remove(courseLecturer);
    }

    public void removeByCourseId(Integer courseId) {
        this.loadByCourseId(courseId).forEach(this::remove);
    }
}
