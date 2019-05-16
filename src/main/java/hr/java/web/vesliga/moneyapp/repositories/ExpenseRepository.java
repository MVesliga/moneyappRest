package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Expense;
import org.hibernate.query.criteria.internal.compile.ExplicitParameterInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Iterable<Expense> findAll();
    Optional<Expense> findById(Long id);
    void deleteById(Long id);
    Expense save(Expense expense);
    List<Expense> findByExpenseNameLike(String expenseName);

    @Query("select e from  Expense e  where e.expenseName = :expenseName and e.type = :expenseType and e.amount between :priceFrom and :priceTo")
    List<Expense> pretrazi(@Param("expenseName") String expenseName,
                               @Param("expenseType")Expense.ExpenseType expenseType,
                               @Param("priceFrom")Double priceFrom,
                               @Param("priceTo")Double priceTo);
    @Query("select e from  Expense e  where e.expenseName = :expenseName and e.type = :expenseType")
    List<Expense>pretraziImeTip(@Param("expenseName") String expenseName,
                                @Param("expenseType")Expense.ExpenseType expenseType);
    @Transactional
    @Modifying
    @Query("delete from Expense e where e.wallet.id = :id")
    void resetWallet(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Expense e set e = :expense where e.id = :id")
    void update(@Param("id") Long id, @Param("expense") Expense expense);
}
