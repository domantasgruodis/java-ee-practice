package lt.vu.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSE")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course as c"),
        @NamedQuery(name = "Course.findAllByStudyProgrammeId", query = "select c from Course as c where c.studyProgramme.id =: studyProgrammeId")
})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CREDITS")
    private Integer credits;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "STUDY_PROGRAMME_ID")
    private StudyProgramme studyProgramme;

    @OneToMany(mappedBy = "course")
    private List<CourseLecturer> courseLecturers;
}
