package com.sprint.filmerentalstore.controller.negative;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.PaymentDto;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.PaymentService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerNegativeTest {
 
	@MockBean
	private PaymentService service;
	
	@Autowired
	private MockMvc mockmvc;
	
	@Test
    public void testGetAllPayment_Error() throws Exception {
        when(service.getAllPayment()).thenThrow(new FilmRentalStoreException("Error retrieving payments"));
        mockmvc.perform(get("/payment"))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void testGetPaymentByIdData_Error() throws Exception {
        short paymentId = 1;
        when(service.getPaymentById(paymentId)).thenThrow(new FilmRentalStoreException("not found"));
        mockmvc.perform(get("/payment/{id}", paymentId))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void testGetRevenueDatewise_Error() throws Exception {
        when(service.claculateCumalativeRevenueDatewise()).thenThrow(new FilmRentalStoreException("not found"));
       mockmvc.perform(get("/payment/revenue/datewise"))
                .andExpect(status().isNotFound());
    }

	@Test
    public void testGetRevenueDatewiseByStoreId_Error() throws Exception {
        // Arrange
        short storeId = 1;
        when(service.getRevenueByDateAndStore(storeId)).thenThrow(new FilmRentalStoreException("Error retrieving revenue by store"));

       mockmvc.perform(get("/payment/revenue/datewise/store/{storeId}", storeId))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void testGetRevenueByFilm_Error() throws Exception {
        short filmId = 1;
        when(service.getRevenueByFilmId(filmId)).thenThrow(new FilmRentalStoreException("Error retrieving revenue by film"));
        mockmvc.perform(get("/payment/revenue/film/{filmId}", filmId))
                .andExpect(status().isNotFound());
    }
	
	
	public void testGetRevenueByStore_Error() throws Exception {
        short storeId = 1;
        when(service.getRevenueByStoreId(storeId)).thenThrow(new FilmRentalStoreException("Error retrieving revenue by store"));
        mockmvc.perform(get("/payment/revenue/films/store/{storeId}", storeId))
                .andExpect(status().isNotFound());
        
    }
	
	private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	@Test
    public void testAddRental_WhenErrorOccurs() throws Exception {
        // Prepare test data
        PaymentDto payment=new PaymentDto();
        Mockito.doThrow(new FilmRentalStoreException("Error adding rental")).when(service).addPayment(payment);
        mockmvc.perform(MockMvcRequestBuilders.post("/payment/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(payment)))
                .andReturn().getResponse();
        Mockito.verify(service).addPayment(payment);
    }
}

