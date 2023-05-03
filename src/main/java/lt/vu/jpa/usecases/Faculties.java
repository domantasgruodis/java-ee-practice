package lt.vu.jpa.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.jpa.entities.Faculty;
import lt.vu.jpa.persistence.FacultyDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Faculties {

    @Inject
    FacultyDAO facultyDAO;

    @Getter @Setter
    private Faculty newFaculty = new Faculty();

    @Getter
    private List<Faculty> allFaculties;

    @PostConstruct
    public void init() {
        loadAllFaculties();
    }

    @Transactional
    public void createFaculty() {
        this.facultyDAO.persist(newFaculty);
    }

    private void loadAllFaculties() {
        this.allFaculties = facultyDAO.loadAll();
    }
}
