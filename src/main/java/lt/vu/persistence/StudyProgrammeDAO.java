package lt.vu.persistence;

import lt.vu.entities.StudyProgramme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudyProgrammeDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(StudyProgramme studyProgramme) {
        em.persist(studyProgramme);
    }

    public StudyProgramme loadOne(Integer id) {
        return em.find(StudyProgramme.class, id);
    }

    public List<StudyProgramme> loadAll() {
        return em.createNamedQuery("StudyProgramme.findAll", StudyProgramme.class).getResultList();
    }

    public List<StudyProgramme> loadAllByDeviceId(Integer deviceId) {
        return em.createNamedQuery("StudyProgramme.findAllByDeviceId", StudyProgramme.class).setParameter("deviceId", deviceId).getResultList();
    }

    public StudyProgramme update(StudyProgramme studyProgramme) {
        return em.merge(studyProgramme);
    }
}
