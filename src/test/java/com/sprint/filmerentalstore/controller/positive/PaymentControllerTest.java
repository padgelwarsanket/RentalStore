package com.sprint.filmerentalstore.controller.positive;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.PaymentDto;
import com.sprint.filmerentalstore.service.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void testGetAllPayment() throws Exception {
        List<PaymentDto> paymentDtos = Arrays.asList(
                new PaymentDto(),
                new PaymentDto()
        );

        when(paymentService.getAllPayment()).thenReturn(paymentDtos);

        mockMvc.perform(get("/payment"))
                .andExpect(status().isFound());

        verify(paymentService, times(1)).getAllPayment();
    }

    @Test
    public void testGetPaymentByIdData() throws Exception {
        Short paymentId = 1;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(paymentId);

        when(paymentService.getPaymentById(paymentId)).thenReturn(paymentDto);

        mockMvc.perform(get("/payment/{id}", paymentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.paymentId").value(String.valueOf(paymentId))); // Convert paymentId to String

        verify(paymentService, times(1)).getPaymentById(paymentId);
    }


    @Test
    public void testAddPayment() throws Exception {
        PaymentDto paymentDto = new PaymentDto();

        mockMvc.perform(post("/payment/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(paymentDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Created Successfully"));

        verify(paymentService, times(1)).addPayment(paymentDto);
    }

    @Test
    public void testGetRevenueDatewise() throws Exception {
        List<Map<String, Object>> revenueData = Arrays.asList(
                Map.of("date", "2023-01-01", "revenue", 100),
                Map.of("date", "2023-01-02", "revenue", 200)
        );

        when(paymentService.claculateCumalativeRevenueDatewise()).thenReturn(revenueData);

        mockMvc.perform(get("/payment/revenue/datewise"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(revenueData.size()));

        verify(paymentService, times(1)).claculateCumalativeRevenueDatewise();
    }
    
    @Test
    public void testGetRevenueDatewiseByStoreId() throws Exception {
        Short storeId = 1;
        List<Map<String, Object>> revenueData = Arrays.asList(
                Map.of("date", "2023-01-01", "revenue", 100),
                Map.of("date", "2023-01-02", "revenue", 200)
        );

        when(paymentService.getRevenueByDateAndStore(storeId)).thenReturn(revenueData);

        mockMvc.perform(get("/payment/revenue/datewise/store/{storeId}", storeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(revenueData.size()));

        verify(paymentService, times(1)).getRevenueByDateAndStore(storeId);
    }

    @Test
    public void testGetRevenueByFilm() throws Exception {
        Short filmId = 1;
        List<Map<String, Object>> revenueData = Arrays.asList(
                Map.of("date", "2023-01-01", "revenue", 100),
                Map.of("date", "2023-01-02", "revenue", 200)
        );

        when(paymentService.getRevenueByFilmId(filmId)).thenReturn(revenueData);

        mockMvc.perform(get("/payment/revenue/film/{filmId}", filmId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(revenueData.size()));

        verify(paymentService, times(1)).getRevenueByFilmId(filmId);
    }

    @Test
    public void testGetRevenueByStore() throws Exception {
        Short storeId = 1;
        List<Map<String, Object>> revenueData = Arrays.asList(
                Map.of("filmId", 1, "revenue", 100),
                Map.of("filmId", 2, "revenue", 200)
        );

        when(paymentService.getRevenueByStoreId(storeId)).thenReturn(revenueData);

        mockMvc.perform(get("/payment/revenue/films/store/{storeId}", storeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(revenueData.size()));

        verify(paymentService, times(1)).getRevenueByStoreId(storeId);
    }


    // Utility method to convert object to JSON string
    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
