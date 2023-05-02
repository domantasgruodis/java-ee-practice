package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Course;
import lt.vu.entities.Student;
import lt.vu.entities.StudyProgramme;
import lt.vu.persistence.CourseDAO;
import lt.vu.persistence.StudentDAO;
import lt.vu.persistence.StudyProgrammeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Model
public class SelectedStudyProgramme {

    @Inject
    StudyProgrammeDAO studyProgrammeDAO;

    @Inject
    CourseDAO courseDAO;

    @Inject
    StudentDAO studentDAO;

    @Getter @Setter
    private Student newStudent = new Student();

    @Getter @Setter
    private Course newCourse = new Course();

    @Getter
    private StudyProgramme currentStudyProgramme;

    @Getter
    private List<Student> studentsFromStudyProgramme;

    @Getter
    private List<Course> studyProgrammeCourses;

    @PostConstruct
    public void init() {
        loadCurrentStudyProgramme();
        loadAllStudents();
        loadAllCourses();
    }

    @Transactional
    public void createCourse() {
        newCourse.setStudyProgramme(currentStudyProgramme);
        courseDAO.persist(newCourse);
    }

    @Transactional
    public void createStudent() {
        newStudent.setStudyProgramme(currentStudyProgramme);
        newStudent.setStartYear(OffsetDateTime.now().getYear());
        studentDAO.persist(newStudent);
    }

    private void loadCurrentStudyProgramme() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt(requestParameters.get("id"));
        this.currentStudyProgramme = studyProgrammeDAO.loadOne(id);
    }

    private void loadAllStudents() {
        studentsFromStudyProgramme = studentDAO.loadAllByStudyProgrammeId(currentStudyProgramme.getId());
    }

    private void loadAllCourses() {
        studyProgrammeCourses = courseDAO.loadAllByStudyProgrammeId(currentStudyProgramme.getId());
    }

}
