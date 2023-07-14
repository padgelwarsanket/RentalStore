package com.sprint.filmerentalstore.controller.negative;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.RentalService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerNegativeTest {

	@MockBean
	private RentalService rentalservice;
	@Autowired
	private MockMvc mockmvc;
	
	@Test
    public void testGetAllRentalData_WhenErrorOccurs() throws Exception {
        Mockito.when(rentalservice.getAllRental()).thenThrow(new FilmRentalStoreException("Error retrieving rental data"));
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.get("/rental"))
                .andReturn().getResponse();
        Mockito.verify(rentalservice).getAllRental();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
	
	@Test
    public void testAddRental_WhenErrorOccurs() throws Exception {
        // Prepare test data
        RentalDto rentalDto = new RentalDto(/* rental data */);
        Mockito.doThrow(new FilmRentalStoreException("Error adding rental")).when(rentalservice).addRental(rentalDto);
        mockmvc.perform(MockMvcRequestBuilders.post("/rental/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rentalDto)))
                .andReturn().getResponse();
        Mockito.verify(rentalservice).addRental(rentalDto);
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
	void testGetRentalByIdData_NotFound() throws Exception {
	    Short rentalId = 1;
	    when(rentalservice.getRentalById(rentalId)).thenReturn(null);

	    mockmvc.perform(MockMvcRequestBuilders.get("/rentals/{id}", rentalId))
	            .andExpect(status().isNotFound());
	}
	
	
	@Test
	void testGetTopTenRentedFilms_NoFilmsFound() throws Exception {
	    when(rentalservice.getTopTenRentedFilms()).thenThrow(new FilmRentalStoreException("no films found"));
	    mockmvc.perform(MockMvcRequestBuilders.get("/rental/toptenfilms"))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	public void testGetFilmsByCustomer_NoFilmsFound() throws Exception {
	    Short customerId = 1;
	    when(rentalservice.getFilmsByCustomer(customerId)).thenThrow(new FilmRentalStoreException("no films found"));

	    mockmvc.perform(MockMvcRequestBuilders.get("/rental/customer/{id}", customerId))
	            .andExpect(status().isNotFound());
	}
	
	
	@Test
	public void testGetTopTenRentedFilmsByStore_NoFilmsFound() throws Exception {
	    when(rentalservice.getTopTenRentedFilmsByStore((short)2)).thenThrow(new FilmRentalStoreException("no films found"));
	    mockmvc.perform(MockMvcRequestBuilders.get("/rental/toptenfilms/store/{id}",2))
	            .andExpect(status().isNotFound());
	}
	
	

	@Test
	public void getCustomersWithPendingReturns() throws Exception {
	    when(rentalservice.getCustomersWithPendingReturns((short)2)).thenThrow(new FilmRentalStoreException("no films found"));
	    mockmvc.perform(MockMvcRequestBuilders.get("/rental/due/store/{id}",2))
	            .andExpect(status().isNotFound());
	}



	//@Test
    public void testUpdateReturnDate_RentalNotFound() throws Exception {
        Short rentalId = 1;
        LocalDate returnDate = LocalDate.now();
        doThrow(new FilmRentalStoreException("Rental not found")).when(rentalservice).updateReturnDate(rentalId, returnDate);
        mockmvc.perform(put("/rental/update/returndate/{id}", rentalId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnDate)))
                .andExpect(status().isNotFound());
        verify(rentalservice).updateReturnDate(rentalId, returnDate);
    }
}
