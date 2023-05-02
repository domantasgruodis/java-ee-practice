package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LECTURER")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Lecturer.findAll", query = "select l from Lecturer as l"),
        @NamedQuery(name = "Lecturer.findAllByFacultyId", query = "select l from Lecturer as l where l.faculty.id =: facultyId")
})
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "FACULTY_ID")
    private Faculty faculty;

    @OneToMany(mappedBy = "lecturer")
    private List<CourseLecturer> lecturedCourses;
}
