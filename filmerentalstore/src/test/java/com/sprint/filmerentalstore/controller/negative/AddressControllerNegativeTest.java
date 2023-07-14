package com.sprint.filmerentalstore.controller.negative;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.serviceimpl.AddressServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AddressConTrollerNegativeTest {
	
	@MockBean
    private AddressServiceImpl addressService;

    @Autowired
    private MockMvc mockmvc;
    

    @Test
    void testGetAllAddress_NotFound() throws Exception {
        when(addressService.getAllAddress()).thenThrow(new FilmRentalStoreException("not found"));
        mockmvc.perform(get("/address"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testGetAddressById_NotFound() throws Exception {
        Short id =1;
        AddressDto address = new AddressDto();
        when(addressService.getAddressById(id)).thenThrow(new FilmRentalStoreException("not found"));
        mockmvc.perform(get("/address/id/{id}", id))
                .andExpect(status().isNotFound());
    }

    
}

