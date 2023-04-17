package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSE")
@Getter
@Setter
@NamedQuery(name = "Course.findAll", query = "select c from Course as c")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CREDITS")
    private Integer credits;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<CourseLecturer> courseLecturers;
}
