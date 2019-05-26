package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    public void testFindAll() throws Exception {
                 this.mockMvc.perform(get("/api/expenses")).andExpect(status().isOk()).andExpect(content()
                 .json("[{'createDate': '2019-05-16T17:00:00','expenseName' : 'Novi trošak','amount': 300.0,'type':'Dugovanje'}]"));
    }

    @Test
    public void testFindOne() throws Exception{
        this.mockMvc.perform(get("/api/expenses/2")).andExpect(status().isOk()).andExpect(content()
                .json("{'createDate': '2019-05-16T17:00:00','expenseName' : 'Novi trošak','amount': 300.0,'type':'Dugovanje'}"));
    }

    @Test
    public void testSave() throws Exception{
              mockMvc.perform(post("/api/expenses").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"expenseName\":\"Trosak\",\"type\":\"Dugovanje\",\"amount\":50}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"expenseName\":\"Trosak\",\"type\":\"Dugovanje\",\"amount\":50}"));
    }

    @Test
    public void testUpdate()throws Exception {
        this.mockMvc.perform(put("/api/expenses/2").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"expenseName\":\"TrosakEdit\",\"type\":\"Dugovanje\",\"amount\":50}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception{
        this.mockMvc.perform(delete("/api/expenses/2")).andExpect(status().is2xxSuccessful());
    }
}