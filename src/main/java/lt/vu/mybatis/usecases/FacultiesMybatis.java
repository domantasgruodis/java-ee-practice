package lt.vu.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.model.Faculty;
import lt.vu.mybatis.dao.FacultyMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class FacultiesMybatis {

    @Inject
    FacultyMapper facultyMapper;

    @Getter
    @Setter
    private Faculty newFaculty = new Faculty();

    @Getter
    private List<Faculty> allFaculties;

    @PostConstruct
    public void init() {
        loadAllFaculties();
    }

    @Transactional
    public void createFaculty() {
        this.facultyMapper.insert(newFaculty);
    }

    private void loadAllFaculties() {
        this.allFaculties = facultyMapper.selectAll();
    }
}
