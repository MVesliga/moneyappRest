package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WalletRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WalletRepository walletRepository;

    @Test
    public void findAll() throws Exception{
        this.mockMvc.perform(get("/api/wallets")).andExpect(status().isOk()).andExpect(content()
                .json("[{'id': 1,'walletName' : 'Admin wallet','walletType': 'Gotovina','userName':'admin'}]"));
    }

    @Test
    public void findOne() throws Exception{
        this.mockMvc.perform(get("/api/wallets/1")).andExpect(status().isOk()).andExpect(content()
                .json("{'id': 1,'walletName' : 'Admin wallet','walletType': 'Gotovina','userName':'admin'}"));
    }
}