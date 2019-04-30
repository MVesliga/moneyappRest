package hr.java.web.vesliga.moneyapp.repositories;


import com.fasterxml.jackson.annotation.JsonBackReference;
import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import org.hibernate.Session;
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
public class HibernateExpenseRepository implements ExpenseRepository {

    private final EntityManager em;
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    public HibernateExpenseRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Iterable<Expense> findAll() {
        Session session = (Session) em.getDelegate();
        return session.createQuery("SELECT e FROM Expense e",Expense.class).getResultList();
    }

    @Override
    public Expense findOne(Long id) {
        Session session = (Session) em.getDelegate();
        Expense e =  session.find(Expense.class,id);
        return e;
    }

    @Override
    public Expense save(Expense expense) {
        Session session = (Session)em.getDelegate();
        expense.setCreateDate(LocalDateTime.now());
        Serializable id = session.save(expense);
        expense.setId((Long)id);
        //System.out.println("WALLET: " +expense.getWallet().getId().toString());
        return expense;
    }

    @Override
    public void delete(Long id) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();
        session.createQuery("DELETE Expense e WHERE e.id = :id").setParameter("id",id).executeUpdate();
    }

    @Override
    public void resetWallet(Long id) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();
        session.createQuery("DELETE Expense e WHERE e.wallet.id = :id").setParameter("id",id).executeUpdate();
    }

    @Override
    public void update(Long id, Expense expense) {
        Session session = (Session)em.getDelegate();
        em.joinTransaction();

        Expense editedExpense  = expenseRepository.findOne(id);

        editedExpense.setCreateDate(LocalDateTime.now());
        editedExpense.setExpenseName(expense.getExpenseName());
        editedExpense.setAmount(expense.getAmount());
        editedExpense.setType(expense.getType());
        editedExpense.setWallet(expense.getWallet());

        session.update(editedExpense);

    }
}
