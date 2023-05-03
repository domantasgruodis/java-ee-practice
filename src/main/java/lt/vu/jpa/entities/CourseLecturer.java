package lt.vu.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_LECTURER")
@Getter
@Setter
@NamedQuery(name = "CourseLecturer.findAllByCourseId", query = "select cl from CourseLecturer as cl where cl.course.id =: courseId")
public class CourseLecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "LECTURER_ID")
    private Lecturer lecturer;
}
