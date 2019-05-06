/*package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@Primary
@Repository
@Transactional
public class HibernateUserRepository implements UserRepository{


    private final EntityManager em;

    @Autowired
    public HibernateUserRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Iterable<User> findAll() {
        Session session = (Session) em.getDelegate();
        return session.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public User findOne(Long id) {
        Session session = (Session) em.getDelegate();
        return (User)session.createQuery("SELECT u FROM User u where u.id = :id").setParameter("id",id).getSingleResult();
    }

    @Override
    public User save(User user) {
        Session session = (Session)em.getDelegate();
        user.setEnabled(true);
        Serializable id = session.save(user);
        user.setId((Long)id);

        return user;
    }

    @Override
    public void delete(Long id) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();
        session.createQuery("DELETE User u WHERE u.id = :id").setParameter("id",id).executeUpdate();
    }

    @Override
    public void update(Long id, User user) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();

        User editedUser = findOne(id);

        editedUser.setUsername(user.getUsername());
        editedUser.setPassword(user.getPassword());
        editedUser.setEnabled(user.isEnabled());

        session.update(editedUser);

    }
}
*/