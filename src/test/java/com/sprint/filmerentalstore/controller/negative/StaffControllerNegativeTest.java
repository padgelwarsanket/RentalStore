package com.sprint.filmerentalstore.controller.negative;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.StaffService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StaffControllerNegativeTest {

	@MockBean
	private StaffService staffservice;
	
	@Autowired
	private MockMvc mockmvc;
	@Test
	void testGetAllStaff_NotFound() throws Exception {
	    when(staffservice.getAllStaff()).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff"))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	void testGetStaffByLastName_NotFound() throws Exception {
	    String lastName = "Smith";
	    when(staffservice.getStaffByLastName(lastName)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/lastname/{lastName}", lastName))
	            .andExpect(status().isNotFound());
	}
	
	
	@Test
	void testGetStaffByFirstName_NotFound() throws Exception {
	    String firstName = "John";
	    when(staffservice.getStaffByFirstName(firstName)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/fn/{firstName}", firstName))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testGetStaffByEmail_NotFound() throws Exception {
	    String email = "jony.stephens@sakilastaff.com";
	    when(staffservice.getStaffByEmail(email)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/email/{email}", email))
	            .andExpect(status().isNotFound());
	}



	@Test
	void testAssignAddressToStaff_NotFound() throws Exception {
	    Short staffId = 1;
	    Staff staff = new Staff();
	    when(staffservice.updateStaffAddress(staffId, staff.getAddressId().getAddressId())).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(put("/staff/{staffId}/address", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}
	
	public static String asJsonString(Object object) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}


	@Test
	void testGetStaffByCity_NotFound() throws Exception {
	    String city = "Lethbridge";
	    when(staffservice.getStaffByCity(city)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/city/{city}", city))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	void testGetStaffByCountry_NotFound() throws Exception {
	    String country = "Some Country";
	    when(staffservice.getStaffByCountry(country)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/country/{country}", country))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testGetStaffByPhone_NotFound() throws Exception {
	    String phone = "14033335568";
	    when(staffservice.getStaffByPhone(phone)).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(get("/staff/phone/{phone}", phone))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	void testUpdateStaffFirstName_NotFound() throws Exception {
	    Short staffId = 1;
	    Staff staff = new Staff();
	    staff.setFirstName("New First Name");
	    when(staffservice.updateStaffFirstName(staffId, staff.getFirstName())).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(put("/staff/update/firstname/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testUpdateStaffLastName_NotFound() throws Exception {
	    Short staffId = 2;
	    Staff staff = new Staff();
	    staff.setLastName("New Last Name");
	    when(staffservice.updateStaffLastName(staffId, staff.getLastName())).thenThrow(new FilmRentalStoreException("not found"));
	    mockmvc.perform(put("/staff/update/lastname/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testUpdateStaffEmail_NotFound() throws Exception {
	    Short staffId = 1;
	    Staff staff = new Staff();
	    staff.setEmail("new-email@example.com");

	    // Mock the service method to return null
	    when(staffservice.updateStaffEmail(staffId, staff.getEmail())).thenThrow(new FilmRentalStoreException("not found"));

	    // Perform the PUT request and check for the status code
	    mockmvc.perform(put("/staff/update/email/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testAssignStoreToStaff_NotFound() throws Exception {
	    Short staffId = 2;
	    Staff staff = new Staff();
	    staff.setStoreId(new Store());

	    // Mock the service method to return null
	    when(staffservice.updateStoreToStaff(staffId, staff.getStoreId().getStoreId())).thenThrow(new FilmRentalStoreException("not found"));

	    // Perform the PUT request and check for the status code
	    mockmvc.perform(put("/staff/update/store/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}

	@Test
	void testUpdateStaffPhoneNumber_NotFound() throws Exception {
	    Short staffId = 2;
	    Staff staff = new Staff();
	    staff.setAddressId(new Address());
	    staff.getAddressId().setPhone("1234567890");

	    // Mock the service method to return null
	    when(staffservice.updateStaffPhoneNumber(staffId, staff.getAddressId().getPhone())).thenThrow(new FilmRentalStoreException("not found"));

	    // Perform the PUT request and check for the status code
	    mockmvc.perform(put("/update/phone/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isNotFound());
	}
}
