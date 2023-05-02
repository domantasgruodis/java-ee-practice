package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Many;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student as s"),
        @NamedQuery(name = "Student.findAllByFacultyId", query = "select s from Student as s where s.studyProgramme.faculty.id =: facultyId"),
        @NamedQuery(name = "Student.findAllByStudyProgrammeId", query = "select s from Student as s where s.studyProgramme.id =: studyProgrammeId")
})

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "START_YEAR")
    private Integer startYear;

    @ManyToOne
    @JoinColumn(name = "STUDY_PROGRAMME_ID")
    private StudyProgramme studyProgramme;
}
