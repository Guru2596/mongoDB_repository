package com.example.mongoDB_repository.controller;
import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.mongoDB_repository.model.Product;
import com.example.mongoDB_repository.model.ProductDiscription;
import com.example.mongoDB_repository.service.ProductServiceImpli;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.example.mongoDB_repository.exception.ProductNotFoundException;




@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductServiceImpli productServiceImpli;
    private Product product1,product2;
    private ProductDiscription productDiscription1,productDiscription2;
    List<Product> productList;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp(){
        productDiscription1 = new ProductDiscription(35000,"SONY");
        product1 = new Product(1001,"SONY max",productDiscription1);
        productDiscription2 = new ProductDiscription(45000,"BLACKBERRY");
        product2 = new Product(1002,"Micro Max",productDiscription2);
        productList = Arrays.asList(product1,product2);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    public void tearDown(){
        product1 = null;
        product2 = null;
    }

    @Test
    public void givenProductToSaveReturnSaveProductSuccess() throws Exception, ProductAlreadyExiistException {
        when(productServiceImpli.saveProduct(any())).thenReturn(product1);
        mockMvc.perform(post("/api/p1/product").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(product1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(productServiceImpli,times(1)).saveProduct(any());
    }

    @Test
    public void givenCustomerToSaveReturnSaveProductFailure() throws ProductAlreadyExiistException, Exception {
        when(productServiceImpli.saveProduct(any())).thenThrow(ProductAlreadyExiistException.class);
        mockMvc.perform(post("/api/p1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productServiceImpli,times(1)).saveProduct(any());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}
