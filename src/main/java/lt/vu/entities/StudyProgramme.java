package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STUDY_PROGRAMME")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "StudyProgramme.findAll", query = "select sp from StudyProgramme as sp"),
        @NamedQuery(name = "StudyProgramme.findAllByFacultyId", query = "select sp from StudyProgramme sp where sp.faculty.id =: facultyId")
})
public class StudyProgramme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "FACULTY_ID")
    private Faculty faculty;
}
