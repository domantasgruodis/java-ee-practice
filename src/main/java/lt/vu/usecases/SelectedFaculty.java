package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.StudyProgramme;
import lt.vu.persistence.FacultyDAO;
import lt.vu.persistence.StudyProgrammeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class SelectedFaculty {

    @Inject
    FacultyDAO facultyDAO;

    @Inject
    StudyProgrammeDAO studyProgrammeDAO;

    @Getter @Setter
    private StudyProgramme newStudyProgramme = new StudyProgramme();

    @Getter
    private lt.vu.entities.Faculty currentFaculty;

    @PostConstruct
    public void init() {
        loadCurrentFaculty();
    }

    @Transactional
    public void createStudyProgramme() {
        this.studyProgrammeDAO.persist(newStudyProgramme);
    }

    private void loadCurrentFaculty() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt(requestParameters.get("id"));
        this.currentFaculty = facultyDAO.loadOne(id);
    }
}
