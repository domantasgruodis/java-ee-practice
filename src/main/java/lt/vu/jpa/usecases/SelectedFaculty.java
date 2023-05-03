package lt.vu.jpa.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.jpa.entities.Lecturer;
import lt.vu.jpa.entities.StudyProgramme;
import lt.vu.jpa.persistence.FacultyDAO;
import lt.vu.jpa.persistence.LecturerDAO;
import lt.vu.jpa.persistence.StudyProgrammeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class SelectedFaculty {

    @Inject
    FacultyDAO facultyDAO;

    @Inject
    StudyProgrammeDAO studyProgrammeDAO;

    @Inject
    LecturerDAO lecturerDAO;

    @Getter @Setter
    private StudyProgramme newStudyProgramme = new StudyProgramme();

    @Getter @Setter
    private Lecturer newLecturer = new Lecturer();

    @Getter
    private lt.vu.jpa.entities.Faculty currentFaculty;

    @Getter
    private List<StudyProgramme> allStudyProgrammes;

    @Getter
    private List<Lecturer> allFacultyLecturers;

    @PostConstruct
    public void init() {
        loadCurrentFaculty();
        loadAllStudyProgrammes();
        loadAllFacultyLecturers();
    }

    @Transactional
    public void createStudyProgramme() {
        newStudyProgramme.setFaculty(facultyDAO.loadOne(currentFaculty.getId()));
        studyProgrammeDAO.persist(newStudyProgramme);
    }

    @Transactional
    public void createLecturer() {
        newLecturer.setFaculty(this.currentFaculty);
        lecturerDAO.persist(newLecturer);
    }

    private void loadAllStudyProgrammes() {
        this.allStudyProgrammes = studyProgrammeDAO.loadAllByFacultyId(currentFaculty.getId());
    }

    private void loadAllFacultyLecturers() {
        this.allFacultyLecturers = lecturerDAO.loadAllByFacultyId(currentFaculty.getId());
    }

    private void loadCurrentFaculty() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt(requestParameters.get("id"));
        this.currentFaculty = facultyDAO.loadOne(id);
    }
}
