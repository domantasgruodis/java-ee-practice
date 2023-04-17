package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LECTURER")
@Getter
@Setter
@NamedQuery(name = "Lecturer.findAll", query = "select l from Lecturer as l")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @OneToMany(mappedBy = "lecturer")
    private List<CourseLecturer> lecturedCourses;
}
