package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FACULTY")
@Getter
@Setter
@NamedQuery(name = "Faculty.findAll", query = "select f from Faculty as f")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", length = 100)
    private String name;
}
