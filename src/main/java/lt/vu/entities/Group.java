package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "GROUP")
@Getter
@Setter
@NamedQuery(name = "Group.findAll", query = "select g from Group as g")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "YEAR")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "STUDY_PROGRAMME")
    private StudyProgramme studyProgramme;
}
