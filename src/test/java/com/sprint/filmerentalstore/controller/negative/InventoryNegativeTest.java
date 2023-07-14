package com.sprint.filmerentalstore.controller.negative;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.InventoryDto;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.InventoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InventoryNegativeTest {

	@MockBean
	private InventoryService inventory;
    
    @Autowired
    private MockMvc mockmvc;
    
    @Test
    public void testGetAllInventory_WhenErrorOccurs() throws Exception {
        when(inventory.getAllInventory()).thenThrow(new FilmRentalStoreException("Error retrieving inventory"));
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.get("/inventory/films"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
    
    
    
    @Test
    public void testGetInventoryByStoreId_WhenErrorOccurs() throws Exception {
        Short storeId = 123;
        Mockito.when(inventory.getInventoryByStoreId(storeId)).thenThrow(new FilmRentalStoreException("Error retrieving inventory by storeId"));
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.get("/inventory/store/{id}", storeId))
                .andReturn().getResponse();
        Mockito.verify(inventory).getInventoryByStoreId(storeId);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }


    @Test
    public void testGetInventoryByFilmId_WhenErrorOccurs() throws Exception {
        Short filmId = 456;
        Mockito.when(inventory.getInventoryByFilmId(filmId)).thenThrow(new FilmRentalStoreException("Error retrieving inventory by filmId"));
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.get("/inventory/film/{id}", filmId))
                .andReturn().getResponse();
        Mockito.verify(inventory).getInventoryByFilmId(filmId);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
    
    @Test
    public void testGetInventoryByFilmIdAndStoreId_WhenErrorOccurs() throws Exception {
        Short filmId = 456;
        Short storeId = 123;
        Mockito.when(inventory.getInventoryByFilmIdAndStoreId(filmId, storeId)).thenThrow(new FilmRentalStoreException("Error retrieving inventory by filmId and storeId"));
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.get("/inventory/film/{filmId}/store/{storeId}", filmId, storeId))
                .andReturn().getResponse();
        Mockito.verify(inventory).getInventoryByFilmIdAndStoreId(filmId, storeId);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
    
    
    
    
   //-----------------add-----------------
    @Test
    public void testAddFilmToStore_WhenErrorOccurs() throws Exception {
        InventoryDto inventoryDto = new InventoryDto();
        Mockito.doThrow(new FilmRentalStoreException("Error adding inventory")).when(inventory).addInventory(inventoryDto);
        MockHttpServletResponse response = mockmvc.perform(MockMvcRequestBuilders.post("/inventory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inventoryDto)))
                .andReturn().getResponse();
        Mockito.verify(inventory).addInventory(inventoryDto);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
