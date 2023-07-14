package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.dto.StoreDto;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Test
    public void testGetAllStore() throws Exception {
        List<StoreDto> storeDtos = Arrays.asList(new StoreDto(), new StoreDto());
        when(storeService.getAllStore()).thenReturn(storeDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/store"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(storeDtos.size()));

        verify(storeService, times(1)).getAllStore();
    }

    @Test
    public void testAddStore() throws Exception {
        StoreDto storeDto = new StoreDto();
        storeDto.setStoreId((short) 1);
        storeDto.setManagerStaffId((short) 2);

        when(storeService.registerCustomer(any(StoreDto.class))).thenReturn(storeDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(storeDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storeId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.managerStaffId").value(2));

        verify(storeService, times(1)).registerCustomer(any(StoreDto.class));
    }

    @Test
    public void testUpdateStoreByAddress() throws Exception {
        Short storeId = 1;
        Short addressId = 2;

        when(storeService.updateStoreByAddress(storeId, addressId)).thenReturn(new StoreDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/store/{storeId}/address", storeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(addressId)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(storeService, times(1)).updateStoreByAddress(storeId, addressId);
    }


    @Test
    public void testGetStoreByCountry() throws Exception {
        String country = "Canada";
        List<StoreDto> stores = Arrays.asList(new StoreDto(), new StoreDto());
        when(storeService.getStoreByCountry(country)).thenReturn(stores);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/country/{country}", country))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(stores.size()));

        verify(storeService, times(1)).getStoreByCountry(country);
    }

    @Test
    public void testGetStoreByPhone() throws Exception {
        String phone = "14033335568";
        StoreDto storeDto = new StoreDto();
        when(storeService.getStoreByPhone(phone)).thenReturn(storeDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/phone/{phone}", phone))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(storeService, times(1)).getStoreByPhone(phone);
    }

    @Test
    public void testUpdateStoreManager() throws Exception {
        Short storeId = 4;
        Store store = new Store();
        store.setManagerStaffId((short) 1);

        when(storeService.updateStorManager(storeId, store.getManagerStaffId())).thenReturn(new StoreDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/store/{storeId}/manager", storeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(store)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(storeService, times(1)).updateStorManager(storeId, store.getManagerStaffId());
    }

    @Test
    public void testGetStaffOfStore() throws Exception {
        Short storeId = 4;
        List<StaffDto> staffList = Arrays.asList(new StaffDto(), new StaffDto());
        when(storeService.getStaffOfStore(storeId)).thenReturn(staffList);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/staff/{storeId}", storeId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(storeService, times(1)).getStaffOfStore(storeId);
    }

    @Test
    public void testGetCustomersOfStore() throws Exception {
        Short storeId = 3;
        List<CustomerDto> customerList = Arrays.asList(new CustomerDto(), new CustomerDto());
        when(storeService.getCustomersOfStore(storeId)).thenReturn(customerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/customers/{storeId}", storeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(customerList.size()));

        verify(storeService, times(1)).getCustomersOfStore(storeId);
    }

    @Test
    public void testGetManagerOfStore() throws Exception {
        Short id = 1;
        StaffDto manager = new StaffDto();
        when(storeService.getManagerOfStore(id)).thenReturn(manager);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/manager/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(storeService, times(1)).getManagerOfStore(id);
    }
    
    @Test
    public void testGetStoreByCity() throws Exception {
        String city = "Lethbridge";
        List<StoreDto> stores = Arrays.asList(new StoreDto(), new StoreDto());
        when(storeService.getStoreByCity(city)).thenReturn(stores);

        mockMvc.perform(MockMvcRequestBuilders.get("/store/city/{city}", city))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(stores.size()));

        verify(storeService, times(1)).getStoreByCity(city);
    }

    // Negative test case: Add store with null StoreDto
    @Test
    public void testAddStore_NullStoreDto() throws Exception {
        when(storeService.registerCustomer(any(StoreDto.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(storeService, times(0)).registerCustomer(any(StoreDto.class));
    }

    // Negative test case: Add store with missing required fields
    @Test
    public void testAddStore_MissingRequiredFields() throws Exception {
        StoreDto storeDto = new StoreDto();
        storeDto.setManagerStaffId((short) 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(storeDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
                // Update the assertion to expect a Bad Request status (400)

        verify(storeService, times(0)).registerCustomer(any(StoreDto.class));
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
