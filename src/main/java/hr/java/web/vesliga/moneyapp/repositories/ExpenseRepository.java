package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Expense;

public interface ExpenseRepository {
    Iterable<Expense> findAll();
    Expense findOne(Long id);
    Expense save(Expense expense);
    void delete(Long id);
    void resetWallet(Long id);
    void update(Long id, Expense expense);
}
