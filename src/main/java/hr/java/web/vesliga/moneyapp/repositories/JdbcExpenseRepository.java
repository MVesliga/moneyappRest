/*package hr.java.web.vesliga.moneyapp.repositories;

import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcExpenseRepository implements ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert expenseInserter;

    public JdbcExpenseRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.expenseInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Expenses").usingGeneratedKeyColumns("id");
    }

    @Override
    public Iterable<Expense> findAll() {
        return jdbcTemplate.query("select * from Expenses", this::mapRowToExpense);
    }

    @Override
    public Expense findOne(Long id) {
        return jdbcTemplate.queryForObject("select * from Expenses where id = ?", this::mapRowToExpense, id);
    }

    @Override
    public Expense save(Expense expense) {
        expense.setCreateDate(LocalDateTime.now());
        expense.setId(saveExpenseDetails(expense));

        return expense;
    }

    @Override
    public void delete(Long walletId) {
        jdbcTemplate.update("delete from Expenses where walletId = ?", walletId);
    }

    private Expense  mapRowToExpense(ResultSet resultSet, int rowNum)throws SQLException {
        Expense expense = new Expense();
        expense.setId(resultSet.getLong("id"));
        expense.setExpenseName(resultSet.getString("expenseName"));
        expense.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
        expense.setAmount(resultSet.getDouble("amount"));
        expense.setType(Expense.expenseType.valueOf(resultSet.getString("expenseType")));
        //expense.setWallet(resultSet.getObject("walletId"));

        return expense;
    }

    private long saveExpenseDetails(Expense expense){
        Map<String,Object> values = new HashMap<>();
        values.put("expenseName", expense.getExpenseName());
        values.put("createDate", expense.getCreateDate());
        values.put("amount", expense.getAmount());
        values.put("expenseType", expense.getType());
        //values.put("walletId", expense.getWalletId());

        return expenseInserter.executeAndReturnKey(values).longValue();
    }


    @Override
    public void resetWallet(Long id) {

    }

    @Override
    public void update(Long id, Expense expense) {

    }
}
*/