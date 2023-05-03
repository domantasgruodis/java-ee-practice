package lt.vu.jpa.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.jpa.entities.StudyProgramme;
import lt.vu.jpa.persistence.StudyProgrammeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class StudyProgrammes {

    @Inject
    StudyProgrammeDAO studyProgrammeDAO;

    @Getter @Setter
    private StudyProgramme newStudyProgramme = new StudyProgramme();

    @Getter
    private List<StudyProgramme> allStudyProgrammes;

    @PostConstruct
    public void init() {
        loadAllStudyProgrammes();
    }

    @Transactional
    public void createStudyProgramme() {
        this.studyProgrammeDAO.persist(newStudyProgramme);
    }

    private void loadAllStudyProgrammes() {
        this.allStudyProgrammes = studyProgrammeDAO.loadAll();
    }
}
