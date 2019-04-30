package hr.java.web.vesliga.moneyapp.repositories;

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
    public User findOne(String username) {
        Session session = (Session) em.getDelegate();
        return (User)session.createQuery("SELECT u FROM User u where u.username = :username").setParameter("username",username).getSingleResult();
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
    public void delete(User user) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();
        session.delete(user);
    }
}
