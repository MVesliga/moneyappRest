package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    public void findAll() throws Exception {
                 this.mockMvc.perform(get("/api/expenses")).andExpect(status().isOk()).andExpect(content()
                 .json("[{'id': 1,'createDate': '2019-05-15T13:45:50','expenseName' : 'Jagode','amount': 15.0,'type':'Hrana'}]"));
    }

    @Test
    public void findOne() throws Exception{
        this.mockMvc.perform(get("/api/expenses/1")).andExpect(status().isOk()).andExpect(content()
                .json("{'id': 1,'createDate': '2019-05-15T13:45:50','expenseName' : 'Jagode','amount': 15.0,'type':'Hrana'}"));
    }

    @Test
    public void save() throws Exception{
              mockMvc.perform(post("/api/expenses").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'expenseName':'Trosak','type':'Dugovanje','Amount':50}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{'id':2,'createDate': '2019-05-15T13:50:00','expenseName':Trosak','amount':'50','type':'Dugovanje'}"));
    }

    @Test
    public void update()throws Exception {
        this.mockMvc.perform(put("/api/expenses/1").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'expenseName':'TrosakEdit','type':'Dugovanje','Amount':50}"))
                .andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/expenses/1")).andExpect(status().is2xxSuccessful());
    }
}