package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.serviceimpl.AddressServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {
	
	
	@Autowired
    private MockMvc mockMvc;

 

    @MockBean
    private AddressServiceImpl addressService;

 

    @Test
    public void testGetAllAddress() throws Exception {
        List<AddressDto> addresses = Arrays.asList(
//                new AddressDto(1, "Street 1", "City 1", "State 1", "12345"),
//                new AddressDto(2, "Street 2", "City 2", "State 2", "67890")
        );

 

        when(addressService.getAllAddress()).thenReturn(addresses);

 

        mockMvc.perform(get("/address"))
                .andExpect(status().isFound());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(addresses.size()));

 

        verify(addressService, times(1)).getAllAddress();
    }

 

    @Test
    public void testGetAddressById() throws Exception {
        Short id = 1;
        AddressDto address = new AddressDto();

 

        when(addressService.getAddressById(id)).thenReturn(address);

 

        mockMvc.perform(get("/address/id/{id}", id))
                .andExpect(status().isFound());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.street").value("Street 1"))
//                .andExpect(jsonPath("$.city").value("City 1"))
//                .andExpect(jsonPath("$.state").value("State 1"))
//                .andExpect(jsonPath("$.zipCode").value("12345"));

 

        verify(addressService, times(1)).getAddressById(id);
    }
}

