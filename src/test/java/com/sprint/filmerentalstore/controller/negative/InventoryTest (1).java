package com.sprint.filmerentalstore.controller.negative;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.InventoryDto;
import com.sprint.filmerentalstore.entity.Inventory;
import com.sprint.filmerentalstore.service.InventoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InventoryTest {
    @MockBean
	private InventoryService inventory;
    
    @Autowired
    private MockMvc mockmvc;
    
    /*@Test
	public void testAddFilmToStorey()throws Exception{
		Inventory dto=new Inventory();
		Store sdto= new Store();
		Film fdto=new Film();
		dto.setFilmId(fdto);
		dto.setInventoryId((short)1);
		dto.setStoreId(sdto);
		dto.setLastUpdate(Timestamp.valueOf("2023-02-12 00:00:00"));
		String str="done";
		when(inventory.addInventory(any(Inventory.class))).thenReturn(str);
		mockmvc.perform(post("/inventory/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
		verify(inventory, times(1)).addInventory(any(Inventory.class));
	}*/

	public static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
    
    
    
//    @Test
//    public void testAddFilmToStore() throws Exception {
//        // Prepare test data
//        Inventory inventorydto = new Inventory(/* inventory data */);
//
//        // Mock the service method
//        when(inventory.addInventory(inventorydto)).thenReturn("Record Created Successfully");
//
//        // Perform the POST request
//        mockmvc.perform(MockMvcRequestBuilders.post("/inventory/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(inventorydto)))
//                .andExpect(status().isCreated())
//                .andExpect(content().string("Record Created Successfully"));
//    }
    
    @Test
    public void testGetAllInventory() throws Exception {
        List<InventoryDto> inventoryList = Arrays.asList(new InventoryDto());
        when(inventory.getAllInventory()).thenReturn(inventoryList);
        mockmvc.perform(MockMvcRequestBuilders.get("/inventory/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(inventoryList.size()));
    }
	
    
    @Test
    public void testGetInventoryByStoreId() throws Exception {
        Short storeId = 123;
        List<InventoryDto> inventoryList = Arrays.asList(new InventoryDto());
        when(inventory.getInventoryByStoreId(storeId)).thenReturn(inventoryList);
        mockmvc.perform(MockMvcRequestBuilders.get("/inventory/store/{id}", storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(inventoryList.size()));
    }

    @Test
    public void testGetInventoryByFilmId() throws Exception {
        Short filmId = 456;
        List<InventoryDto> inventoryList = Arrays.asList(new InventoryDto());
        when(inventory.getInventoryByFilmId(filmId)).thenReturn(inventoryList);
        mockmvc.perform(MockMvcRequestBuilders.get("/inventory/film/{id}", filmId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(inventoryList.size()));
    }

    @Test
    public void testGetInventoryByFilmIdAndStoreId() throws Exception {
        Short filmId = 456;
        Short storeId = 123;
        List<InventoryDto> inventoryList = Arrays.asList(new InventoryDto());
        when(inventory.getInventoryByFilmIdAndStoreId(filmId, storeId)).thenReturn(inventoryList);
        
        mockmvc.perform(MockMvcRequestBuilders.get("/inventory/store/{id}", storeId))
                .andExpect(status().isOk());
    }

        
    }





