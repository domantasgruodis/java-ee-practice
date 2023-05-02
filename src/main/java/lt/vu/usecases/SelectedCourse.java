package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Course;
import lt.vu.entities.CourseLecturer;
import lt.vu.entities.Lecturer;
import lt.vu.persistence.CourseDAO;
import lt.vu.persistence.CourseLecturerDAO;
import lt.vu.persistence.LecturerDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Model
public class SelectedCourse {

    @Inject
    CourseDAO courseDAO;

    @Inject
    LecturerDAO lecturerDAO;

    @Inject
    CourseLecturerDAO courseLecturerDAO;

    @Getter
    @Setter
    private Integer[] selectedLecturerIds;

    @Getter
    private List<Lecturer> courseLecturers;

    @Getter
    private Course currentCourse;

    @Getter
    private List<Lecturer> allFacultyLecturers;

    @PostConstruct
    public void init() {
        loadCurrentCourse();
        loadAllFacultyLecturers();
        loadCourseLecturers();
    }

    @Transactional
    public void updateCourseLecturers() {
        courseLecturerDAO.removeByCourseId(currentCourse.getId());
        for (Integer lecturerId : selectedLecturerIds) {
            CourseLecturer courseLecturer = new CourseLecturer();
            courseLecturer.setCourse(currentCourse);
            courseLecturer.setLecturer(lecturerDAO.loadOne(lecturerId));
            courseLecturerDAO.persist(courseLecturer);
        }
    }

    private void loadCurrentCourse() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = Integer.parseInt(requestParameters.get("id"));
        this.currentCourse = courseDAO.loadOne(id);
    }

    private void loadAllFacultyLecturers() {
        allFacultyLecturers = lecturerDAO.loadAllByFacultyId(currentCourse.getStudyProgramme().getFaculty().getId());
    }

    private void loadCourseLecturers() {
        Supplier<Stream<Lecturer>> lecturerStreamSupplier = () -> currentCourse.getCourseLecturers()
                .stream().map(CourseLecturer::getLecturer);
        courseLecturers = lecturerStreamSupplier.get().collect(Collectors.toList());
        selectedLecturerIds = lecturerStreamSupplier.get().map(Lecturer::getId).toArray(Integer[]::new);
    }
}
