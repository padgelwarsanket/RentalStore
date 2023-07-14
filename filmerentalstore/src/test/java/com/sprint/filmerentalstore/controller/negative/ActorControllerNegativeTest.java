package com.sprint.filmerentalstore.controller.negative;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.CustomDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.entity.Actor;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.ActorService;
import com.sprint.filmerentalstore.service.FilmActorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ActorControllerNegativeTest {

	@MockBean
    private ActorService actorService;
	
	@MockBean
    private FilmActorService filmActorService;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetAll_Negative() throws Exception {
        List<ActorDto> actors = Arrays.asList(new ActorDto(), new ActorDto());
        
        // Modify the mock behavior to return an empty list
        when(actorService.searchAll()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/actors/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Expect an empty JSON array
        verify(actorService, times(1)).searchAll();
    }
    
    
    @Test
    public void testCreateActor_Failure() throws Exception {
        ActorDto actorDto = new ActorDto();
        actorDto.setId((short) 1);
        actorDto.setFirstName("Nick");
        actorDto.setLastName("Chase");
        actorDto.setLastUpdate(LocalDateTime.now());
        when(actorService.createActor(any(ActorDto.class))).thenReturn(null);  // Return null to simulate a failure
        mockMvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(actorDto)))
                .andExpect(status().isInternalServerError());  // Expecting an internal server error status code
    }

    
    
    @Test
    void testGetActorByLastName_NotFound() throws Exception {
        String lastName = "Jane";
        when(actorService.searchActorsByLastName(lastName)).thenThrow(new FilmRentalStoreException("not found"));
        mockMvc.perform(get("/actors/lastname/{ln}", lastName))
                .andExpect(status().isNotFound());
    }
    
    
    @Test
    void testGetActorByFirstName_NotFound() throws Exception {
        String firstName = "Mary";
        when(actorService.searchActorsByFirstName(firstName)).thenThrow(new FilmRentalStoreException("not found"));
        mockMvc.perform(get("/actors/firstName/{fn}", firstName))
                .andExpect(status().isNotFound());
    }
    
    
    @Test
    void testUpdateActorLastName_NotFound() throws Exception {
        Short actorId = 2;
        Actor actor = new Actor();
        actor.setLastName("New Last Name");
        when(actorService.updateActorLastName(actorId, actor.getLastName())).thenThrow(new FilmRentalStoreException("not found"));
        mockMvc.perform(put("/acotrs/update/lastName/{id}", actorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(actor)))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testUpdateActorFirstName_NotFound() throws Exception {
        Short actorId = 1;
        Actor actor = new Actor();
        actor.setFirstName("New First Name");
        when(actorService.updateActorFirstName(actorId, actor.getFirstName())).thenThrow(new FilmRentalStoreException("not found"));
        mockMvc.perform(put("/actors/update/firstname/{id}", actorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(actor)))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testSearchFilmsByActorId_Negative() throws Exception {
        Short actorId = 12345;
        when(filmActorService.searchFilmsByActorId(actorId)).thenReturn(Collections.emptyList()); // Return an empty list

        mockMvc.perform(MockMvcRequestBuilders.get("/actors/{id}/films", actorId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Expecting empty list

        verify(filmActorService, Mockito.times(1)).searchFilmsByActorId(actorId);
    }
    
    @Test
    public void testAssignFilmToActor_FilmNotFound() throws Exception {
        Short actorId = 1;
        FilmDto filmDto = new FilmDto();
        filmDto.setFilmId((short) 1);

        // Simulate film not found by returning an empty list of assigned films
        when(filmActorService.assignFilmToActor(actorId, filmDto.getFilmId())).thenThrow(new FilmRentalStoreException("not found"));

        mockMvc.perform(put("/actors/{id}/film", actorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isNotFound());
    }




    
    @Test
    public void testGetTopTenActorsByFilmCount_EmptyList() throws Exception {
        // Mock an empty list of actors
        List<CustomDto> actors = new ArrayList<>();
        when(actorService.getTopTenActorsByFilmCount()).thenReturn(null);

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/actors/toptenbyfilmcount")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
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
