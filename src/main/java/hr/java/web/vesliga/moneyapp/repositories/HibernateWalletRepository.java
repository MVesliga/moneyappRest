package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Wallet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;

@Primary
@Repository
@Transactional
public class HibernateWalletRepository  implements WalletRepository{

    private final EntityManager em;

    @Autowired
    public HibernateWalletRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Iterable<Wallet> findAll() {
        Session session = (Session) em.getDelegate();
        return session.createQuery("SELECT w FROM Wallet w", Wallet.class).getResultList();
    }

    @Override
    public Wallet findOne(Long id) {
        Session session = (Session) em.getDelegate();
        return session.find(Wallet.class,id);
    }

    @Override
    public Wallet findOneByUsername(String username) {
        Session session = (Session) em.getDelegate();
        return (Wallet)session.createQuery("SELECT w FROM Wallet w where w.userName = :username").setParameter("username",username).getSingleResult();
    }

    @Override
    public Wallet save(Wallet wallet) {
        Session session = (Session)em.getDelegate();
        wallet.setCreateDate(LocalDateTime.now());
        Serializable id = session.save(wallet);
        wallet.setId((Long)id);
        return wallet;
    }

    @Override
    public void delete(Long id) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();
        session.createQuery("DELETE Wallet w WHERE w.id = :id").setParameter("id",id).executeUpdate();
    }
}
