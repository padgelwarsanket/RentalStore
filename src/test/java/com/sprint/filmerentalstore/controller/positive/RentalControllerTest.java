package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.service.RentalService;

@RunWith(SpringRunner.class)

@SpringBootTest

@AutoConfigureMockMvc

class RentalControllerTest {

	@MockBean

	private RentalService rentalservice;

	@Autowired

	private MockMvc mockmvc;
	
	
	@Test
    public void testAddRental() throws Exception {
        RentalDto rentalDto = new RentalDto();
        doNothing().when(rentalservice).addRental(rentalDto);
        mockmvc.perform(post("/rental/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rentalDto)))
                .andExpect(status().isOk());
        verify(rentalservice).addRental(rentalDto);
    }

	@Test
	public void testRentalByIdData() throws Exception {
		Short rentalId = 1;
		RentalDto dto = new RentalDto();
		dto.setRentalId(rentalId);
		when(rentalservice.getRentalById(rentalId)).thenReturn(dto);
		mockmvc.perform(get("/rental/{id}", rentalId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.rentalId").value((int) rentalId));
		verify(rentalservice, times(1)).getRentalById(rentalId);

	}

	   @Test

	    public void testFilmsByCustomer() throws Exception{
	        short id=1;
	        List<String> expectedFilms = Arrays.asList("Film1","Film2","film3","Film4","Film5","film6","Film7","Film8","film9","film10");
	        when(rentalservice.getFilmsByCustomer(id)).thenReturn(expectedFilms);
	        mockmvc.perform(get("/rental/customer/{id}",id))
	        .andExpect(status().isOk());
	    }


	@Test

	public void testTopTenRentedFilms() throws Exception {
		List<String> expectedFilms = Arrays.asList("Film1", "Film2", "film3", "Film4", "Film5", "film6", "Film7",
				"Film8", "film9", "film10");
		when(rentalservice.getTopTenRentedFilms()).thenReturn(expectedFilms);
		mockmvc.perform(get("/rental/toptenfilms"))
				.andExpect(status().isOk());

	}

	@Test

	public void testTopTenRentedFilmsByStore() throws Exception {

		short storeId = 1;

		List<String> expectedFilms = Arrays.asList("Film1", "Film2", "film3", "Film4", "Film5", "film6", "Film7",
				"Film8", "film9", "film10");

		when(rentalservice.getTopTenRentedFilmsByStore(storeId)).thenReturn(expectedFilms);

		mockmvc.perform(get("/rental/toptenfilms/store/{id}", storeId))

				.andExpect(status().isOk());

	}

	@Test

	public void testCustomersWithPendingReturns() throws Exception {

		short storeId = 1;

		List<String> expectedFilms = Arrays.asList("Film1", "Film2", "film3", "Film4", "Film5", "film6");

		when(rentalservice.getCustomersWithPendingReturns(storeId)).thenReturn(expectedFilms);

		mockmvc.perform(get("/rental/due/store/{id}", storeId))

				.andExpect(status().isOk());

	}


	//@Test
    public void testUpdateReturnDate() throws Exception {
        // Prepare test data
        Short rentalId = 123;
        LocalDate returnDate = LocalDate.of(2022, 1, 1);
        RentalDto dto=new RentalDto();
        dto.setReturnDate(returnDate);
        // Mock the service method
        doNothing().when(rentalservice).updateReturnDate(rentalId, returnDate);
        mockmvc.perform(put("/rental/update/returndate/{id}", rentalId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isOk());
               // .andExpect(MockMvcResultMatchers.content().string("Rental Return Date Updated Successfully"));

        // Verify that the service method was called with the correct arguments
       // verify(rentalservice).updateReturnDate(rentalId, returnDate);
    }
	public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
	 


