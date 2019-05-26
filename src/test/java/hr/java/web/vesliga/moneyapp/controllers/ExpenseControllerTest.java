package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Authority;
import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.User;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerTest {

    private SessionFactory sessionFactory;
    private Session session = null;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    ExpenseRepository expenseRepository;

    private MultiValueMap<String, String> expenseMap = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> walletMap = new LinkedMultiValueMap<>();


    Expense expense = new Expense();

    @Autowired
    private MockMvc mockMvc;

   /*@Before
    public void before() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Expense.class)
                .addAnnotatedClass(Wallet.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Authority.class);

        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class","org.h2.Driver");
        configuration.setProperty("hibernate.connection.url","jdbc:h2:mem:testdb");
        configuration.setProperty("hibernate.connection.username","sa");
        configuration.setProperty("hibernate.hbm2ddl.auto","create");

        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();


    }*/

    @Test
    public void testFormLogin() throws Exception{
        this.mockMvc.perform(get("/login").with(user("admin").password("adminpass")))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowForm() throws Exception{
        this.mockMvc.perform(get("/expenses/new").with(user("admin").password("adminpass").roles("USER","ADMIN")).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("expense"))
                .andExpect(model().attributeExists("expenseTypes"))
                .andExpect(view().name("newExpense"));
    }

    @Test
    public void testGetExpenses() throws Exception{
        this.mockMvc.perform(get("/expenses/showExpenses").with(user("admin").password("adminpass").roles("ADMIN","USER")).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("expenses"))
                .andExpect(view().name("allExpensesByUser"));
    }

    @Test
    public void testResetWallet() throws Exception{
       this.mockMvc.perform(get("/expenses/resetWallet").with(user("admin").password("adminpass").roles("ADMIN","USER")).with(csrf()))
       .andExpect(status().is3xxRedirection());
    }

   @Test
    public void testExpensePost() throws Exception{
        expenseMap.add("expenseName","Novi trošak");
        expenseMap.add("amount","300");
        expenseMap.add("type",Expense.ExpenseType.Dugovanje.toString());
        expenseMap.add("walletId","1");



       this.mockMvc.perform(post("/expenses/new").with(user("admin").password("adminpass").roles("USER","ADMIN")).with(csrf())
            .params(expenseMap))
            .andExpect(view().name("acceptedExpense"));
}

   @Test
   public void testExpenseSearch() throws Exception{
        this.mockMvc.perform(get("/expenses/searchForExpenses?expenseName=Jagode").with(user("admin").password("adminpass").roles("USER","ADMIN")).with(csrf()))
        .andExpect(status().isOk())
                .andExpect(model().attributeExists("foundExpenses"))
                .andExpect(model().attributeExists("expenseTypes"))
        .andExpect(view().name("searchForExpenses"));
   }

   /*

   @After
    public void after(){
        session.close();
        sessionFactory.close();
    }*/
}