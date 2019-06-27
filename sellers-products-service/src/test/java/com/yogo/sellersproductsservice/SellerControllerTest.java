package com.yogo.sellersproductsservice;

import com.yogo.sellersproductsservice.controler.SellerControler;
import com.yogo.sellersproductsservice.domain.Seller;
import com.yogo.sellersproductsservice.repo.SellersRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Flux;


@RunWith(SpringRunner.class)
@WebMvcTest(SellerControler.class)
public class SellerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SellersRepo sellersRepo;

    @Test
    public void testGetAll() throws Exception{
        Mockito.when(sellersRepo.findAll()).thenReturn(
                Flux.just(
                        new Seller("0015a82c2db000af6aaaf3ae2ecb0532","santo andre","SP" ),
                        new Seller("001cca7ae9ae17fb1caed9dfb1094831","cariacica","ES"),
                        new Seller("001e6ad469a905060d959994f1b41e4f","sao goncalo","RJ"),
                        new Seller("002100f778ceb8431b7a1020ff7ab48f", "franca","SP"),
                        new Seller("003554e2dce176b5555353e4f3555ac8","goiania","GO" )
                ));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/sellers/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}

