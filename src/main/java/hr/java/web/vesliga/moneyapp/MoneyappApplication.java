package hr.java.web.vesliga.moneyapp;

import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.time.LocalDateTime;

@SpringBootApplication
public class MoneyappApplication  {


    public static void main(String[] args) {
        SpringApplication.run(MoneyappApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {

        Wallet w = new Wallet();
        w.setCreateDate(LocalDateTime.now());
        w.setUserName("admin");
        w.setWalletName("admin wallet");



        Expense expense = new Expense();
        expense.setExpenseName("Sdasd ");
        expense.setAmount(123.0);
        expense.setType(Expense.expenseType.Donacija);
        expense.setCreateDate(LocalDateTime.now());

        expenseRepository.save(expense);

        Expense expense2 = new Expense();
        expense2.setExpenseName("qweqweqwe");
        expense2.setAmount(1233.0);
        expense2.setType(Expense.expenseType.Donacija);
        expense2.setCreateDate(LocalDateTime.now());

        expenseRepository.save(expense2);
    }*/
}
