package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.City;
import com.sprint.filmerentalstore.entity.Country;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.service.StaffService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StaffControllerTest {
	@MockBean
	private StaffService staffservice;
	
	@Autowired
	private MockMvc mockmvc;
	
	@Test
	public void testgetAllStaff() throws Exception{
		StaffDto dto=new StaffDto();
		List<StaffDto> list=Arrays.asList(new StaffDto());
		when(staffservice.getAllStaff()).thenReturn(list);
		mockmvc.perform(get("/staff"))
		.andExpect(status().isFound());
		
	}
	
	@Test
	public void addStaff()throws Exception{
		//Address addressId=1;
		Staff staffdto=new Staff();
		Address address=new Address();
		Store store=new Store();
		staffdto.setStaffId((short) 1);
		staffdto.setFirstName("geetu");
		staffdto.setLastName("adivishnu");
		staffdto.setAddressId(address);
		staffdto.setPicture("bolb");
		staffdto.setEmail("Mike.Hillyer@sakilastaff.com");
		staffdto.setStoreId(store);
		staffdto.setActive("1");
		staffdto.setUsername("Mike");
		staffdto.setPassword("8cb2237d0679ca88db6464eac60da96345513964");
		//staffdto.setLastUpdate(Timestamp.valueOf("2006-02-15 04:44:00.000000"));
		staffdto.setLastUpdate(LocalDateTime.of(2006, 2, 15, 4, 44, 0));
		StaffDto s=copyEntityToDto(staffdto);
		when(staffservice.addStaff(s)).thenReturn(s);

				mockmvc.perform(post("/staff")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(s)))
		                .andExpect(status().isCreated());
		                //.andExpect(jsonPath("$.staffId").value((int)1))
//		                .andExpect(jsonPath("$.firstName").value("geetu"))
//				        .andExpect(jsonPath("$.lastName").value("adivishnu"))
//				        .andExpect(jsonPath("$.addressId").value(address))
//				        .andExpect(jsonPath("$.picture").value("bolb"))
//				        .andExpect(jsonPath("$.email").value("Mike.Hillyer@sakilastaff.com"))
//				        .andExpect(jsonPath("$.storeId").value(store))
//				        .andExpect(jsonPath("$.active").value("1"))
//				        .andExpect(jsonPath("$.username").value("Mike"))
//				        .andExpect(jsonPath("$.password").value("8cb2237d0679ca88db6464eac60da96345513964"));
				        //.andExpect(jsonPath("$.lastUpdate").isEmpty());
				
		//verify(staffservice,times(1)).addStaff(any(StaffDto.class));	
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
	public void testgetStaffByLastName()throws Exception{
		String lastName="adivishu";
		StaffDto staffdtos=new StaffDto();
		staffdtos.setLastName(lastName);
		List<StaffDto> staffList=Arrays.asList(staffdtos);
		when(staffservice.getStaffByLastName(lastName)).thenReturn(staffList);
		mockmvc.perform(get("/staff/lastname/{lastName}",lastName))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].lastName").value("adivishu"));
		
		verify(staffservice,times(1)).getStaffByLastName(lastName);
	}
	
	
	
	
	@Test

    public void testGetStaffByFirstName()throws Exception {
        String firstName = "geetu";
        StaffDto staffdto=new StaffDto();
        staffdto.setFirstName(firstName);
        List<StaffDto> staff=Arrays.asList(staffdto);
        when(staffservice.getStaffByFirstName(firstName)).thenReturn(staff);
        mockmvc.perform(get("/staff/fn/{firstName}",firstName))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").value("geetu"));
        verify(staffservice,times(1)).getStaffByFirstName(firstName);

    }
	
	@Test
	public void testGetStaffByEmail()throws Exception {
        String email = "geetu@gmail.com";
        StaffDto staffdto=new StaffDto();
        staffdto.setEmail(email);
        List<StaffDto> listDto = new ArrayList<>();
        listDto.add(staffdto);
        when(staffservice.getStaffByEmail(email)).thenReturn(listDto);
        mockmvc.perform(get("/staff/email/{email}",email))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("geetu@gmail.com"));
        verify(staffservice,times(1)).getStaffByEmail(email);

    }
    

	
	
	@Test
	public void testGetStaffByCity() throws Exception {
        String city = "tanuku";
        Staff dto =new Staff();
        Store store=new Store();
        Address adddto =new Address();
        City citdto=new City();
        adddto.setCityId(citdto);
        store.setAddressId(adddto);
        dto.setStoreId(store);
        dto.getAddressId().getCityId().setCity(city);
        StaffDto s=copyEntityToDto(dto);
        List<StaffDto> staffList =Arrays.asList(s);
        System.out.println("-------------------------"+dto.getAddressId().getCityId().getCity());
        
        when(staffservice.getStaffByCity(city)).thenReturn(staffList);

        mockmvc.perform(MockMvcRequestBuilders.get("/staff/city/{city}", city))
                .andExpect(status().isOk());
    }

	
	
	@Test
	 public void testGetStaffByCountry() throws Exception{
		 String country="india";
		 Staff dto =new Staff();
		 Store store=new Store();
		 Address adddto =new Address();
	     City citdto=new City();
	     Country countrydto=new Country();
	     countrydto.setCountry(country);
	     citdto.setCountryId(countrydto);
	     adddto.setCityId(citdto);
	     store.setAddressId(adddto);
	     dto.setStoreId(store);
	     dto.getStoreId().getAddressId().getCityId().getCountryId().setCountry(country);
	     StaffDto staffDto=copyEntityToDto(dto);
	     List<StaffDto> staffList =Arrays.asList(staffDto);
	     when(staffservice.getStaffByCountry(country)).thenReturn(staffList);
	     mockmvc.perform(MockMvcRequestBuilders.get("/staff/country/{country}", country))
         .andExpect(status().isOk());
	 }
	
	
   @Test
   public void testGetByPhone()throws Exception{
	   String phone="4567890";
	   Staff dto =new Staff();
	   Address adddto =new Address();
	   dto.setAddressId(adddto);
	   dto.getAddressId().setPhone(phone);
	   StaffDto s=copyEntityToDto(dto);
	   List<StaffDto> staffList =Arrays.asList(s);
	     when(staffservice.getStaffByPhone(phone)).thenReturn(staffList);
	     mockmvc.perform(MockMvcRequestBuilders.get("/staff/phone/{phone}", phone))
       .andExpect(status().isOk());
       
   }
   
   
   @Test
   public void testUpdateStaffFirstName() throws Exception {
       Short staffId = 2;
       StaffDto staffDto = new StaffDto();
       staffDto.setFirstName("geetu"); // Update the first name value to "geetu"
       StaffDto expectedResult = new StaffDto();
       when(staffservice.updateStaffFirstName(staffId, "geetu")).thenReturn(expectedResult); // Update the first name value here as well
       
       mockmvc.perform(put("/staff/update/firstname/{staffId}", staffId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(staffDto)))
               .andExpect(status().isOk());
       
       verify(staffservice, times(1)).updateStaffFirstName(staffId, "geetu"); // Update the first name value here as well
       verifyNoMoreInteractions(staffservice);
   }

   @Test
   public void testUpdateStaffLastName() throws Exception {
       Short staffId = 2;
       StaffDto staff = new StaffDto();
       staff.setLastName("New Last Name");
       when(staffservice.updateStaffLastName(staffId, staff.getLastName())).thenReturn(staff);
       mockmvc.perform(MockMvcRequestBuilders.put("/staff/update/lastname/{staffId}", staffId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(staff)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.lastName").value("New Last Name"));
   }

   @Test
   public void testUpdateStaffEmail() throws Exception {
       Short staffId = 2;
       StaffDto staff = new StaffDto();
       staff.setEmail("newemail@example.com");
       when(staffservice.updateStaffEmail(staffId, staff.getEmail())).thenReturn(staff);
       mockmvc.perform(MockMvcRequestBuilders.put("/staff/update/email/{staffId}", staffId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(staff)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.email").value("newemail@example.com"));
   }

   @Test
   public void testAssignStoreToStaff() throws Exception {
	    Short staffId = 2;
	    Staff staff = new Staff();
	    Store store = new Store();
	    store.setStoreId((short) 1);
	    staff.setStoreId(store);
	    StaffDto s=copyEntityToDto(staff);
	    when(staffservice.updateStoreToStaff(staffId, staff.getStoreId().getStoreId())).thenReturn(s);
	    mockmvc.perform(MockMvcRequestBuilders.put("/staff/update/store/{staffId}", staffId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(staff)))
	            .andExpect(status().isOk());
	}

   @Test
   public void testUpdateStaffPhoneNumber() throws Exception {
       Short staffId = 2;
       Staff staff = new Staff();
	    Address address=new Address();
       address.setPhone("9100506221");
       staff.setAddressId(address);
       StaffDto s=copyEntityToDto(staff);
       when(staffservice.updateStaffPhoneNumber(staffId, staff.getAddressId().getPhone())).thenReturn(s);
       mockmvc.perform(MockMvcRequestBuilders.put("/staff/update/phone/{staffId}", staffId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(staff)))
               .andExpect(status().isOk());
   }

	
   
   @Test
   public void testAssignAddressToStaff() throws Exception {
       Short staffId = 2;
       Staff staff = new Staff();
       Address addressId = new Address();
       addressId.setAddressId((short) 1);
       staff.setAddressId(addressId);
       StaffDto s=copyEntityToDto(staff);
       when(staffservice.updateStaffAddress(staffId, staff.getAddressId().getAddressId())).thenReturn(new StaffDto());
       mockmvc.perform(put("/staff/{staffId}/address", staffId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(staff)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").exists());
   }

   @Test
   public void testGetStaffByPhone() throws Exception {
       // Prepare test data
	   String phone="9234566121";
		 Staff dto =new Staff();
		 Address adddto =new Address();
		 adddto.setPhone(phone);
	     dto.setAddressId((adddto));
	     dto.getAddressId().setPhone("910034566");
	     StaffDto s=copyEntityToDto(dto);
	     List<StaffDto> staffList =Arrays.asList(s);
	     when(staffservice.getStaffByPhone(phone)).thenReturn(staffList);
	     mockmvc.perform(MockMvcRequestBuilders.get("/staff/phone/{phone}", phone))
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(status().isOk());
	 }
   
   
   public StaffDto copyEntityToDto(Staff staff){
		StaffDto dto = new StaffDto();
		dto.setStaffId(staff.getStaffId());
		dto.setFirstName(staff.getFirstName());
		dto.setLastName(staff.getLastName());
		dto.setAddressId(staff.getAddressId().getAddressId());
		dto.setPicture(staff.getPicture());
		dto.setEmail(staff.getEmail());
		dto.setStoreId(staff.getStoreId().getStoreId());
		dto.setActive(staff.getActive());
		dto.setUsername(staff.getUsername());
		dto.setPassword(staff.getPassword());
		dto.setLastUpdate(staff.getLastUpdate());
		return dto;
	}
}
