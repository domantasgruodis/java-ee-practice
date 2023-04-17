package lt.vu.persistence;

import lt.vu.entities.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GroupDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Group group) {
        em.persist(group);
    }

    public Group loadOne(Integer id) {
        return em.find(Group.class, id);
    }

    public List<Group> loadAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }

    public Group update(Group group) {
        return em.merge(group);
    }
}
