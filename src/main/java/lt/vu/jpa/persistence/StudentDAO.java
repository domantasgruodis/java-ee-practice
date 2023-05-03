package lt.vu.jpa.persistence;

import lt.vu.jpa.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Student student) {
        em.persist(student);
    }

    public Student loadOne(Integer id) {
        return em.find(Student.class, id);
    }

    public List<Student> loadAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public List<Student> loadAllByFacultyId(Integer facultyId) {
        return em.createNamedQuery("Student.findAllByFacultyId", Student.class)
                .setParameter("facultyId", facultyId).getResultList();
    }

    public List<Student> loadAllByStudyProgrammeId(Integer studyProgrammeId) {
        return em.createNamedQuery("Student.findAllByStudyProgrammeId", Student.class)
                .setParameter("studyProgrammeId", studyProgrammeId).getResultList();
    }

    public Student update(Student student) {
        return em.merge(student);
    }
}
