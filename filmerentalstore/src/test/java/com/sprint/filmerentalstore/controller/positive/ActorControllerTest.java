package com.sprint.filmerentalstore.controller.positive;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.sprint.filmerentalstore.service.ActorService;
import com.sprint.filmerentalstore.service.FilmActorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ActorControllerTest {
	
	@MockBean
    private ActorService actorService;
	
	@MockBean
    private FilmActorService filmActorService;

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ActorController actorController;
    
    
    
    @Test

    public void testGetAll() throws Exception {
        List<ActorDto> actors = Arrays.asList(new ActorDto(), new ActorDto());
        when(actorService.searchAll()).thenReturn(actors);
        mockMvc.perform(get("/actors/all"))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(actors.size()));
        verify(actorService, times(1)).searchAll();
    }
    

 

    @Test
    public void testCreateActor() throws Exception {
        ActorDto actorDto = new ActorDto();
        actorDto.setId((short) 1);
        actorDto.setFirstName("Nick");
        actorDto.setLastName("Chase");
        actorDto.setLastUpdate(LocalDateTime.now());
        when(actorService.createActor(any(ActorDto.class))).thenReturn(actorDto);
        mockMvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(actorDto)))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE))
//                .andExpect(content().string("Record Created Successfully")) // Verify the response body
//                .andExpect(jsonPath("$.id").value(1))
//                 .andExpect(jsonPath("$.firstName").value("Nick"))
//                 .andExpect(jsonPath("$.lastName").value("Chase"))
//                 .andExpect(jsonPath("$.lastUpdate").isNotEmpty());
//         verify(actorService, times(1)).createActor(actorDto);
    }

 

   

 


    @Test
    public void testGetActorsByLastName() throws Exception{

        String lastName="chase";
        ActorDto actorDtos=new ActorDto();
        actorDtos.setLastName(lastName);
        List<ActorDto> actorList=Arrays.asList(actorDtos);
        when(actorService.searchActorsByLastName(lastName)).thenReturn(actorList);
        mockMvc.perform(get("/actors/lastname/{ln}",lastName))
        .andExpect(status().isOk());
//        .andExpect(jsonPath("$[0].lastName").value("chase"));
//        verify(actorService,times(1)).searchActorsByLastName(lastName);
    }
    
   

    @Test
    public void testGetActorsByFirstName() throws Exception {
        String firstName = "Nick";
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName(firstName);
        List<ActorDto> actorsList = Arrays.asList(actorDto);
        when(actorService.searchActorsByFirstName(firstName)).thenReturn(actorsList);
        mockMvc.perform(get("/actors/firstname/{firstName}", firstName)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Nick"));
        verify(actorService, times(1)).searchActorsByFirstName(firstName);

    }
    
 

    @Test
    public void testUpdateActorLastName() throws Exception {
        Short id = 1;
        String updatedLastName = "Smith";
        ActorDto actorDto = new ActorDto();
        actorDto.setLastName(updatedLastName);
        ActorDto updatedActorDto = new ActorDto();
        updatedActorDto.setId(id);
        updatedActorDto.setLastName(updatedLastName);
        when(actorService.updateActorLastName(id, updatedLastName)).thenReturn(updatedActorDto);
        mockMvc.perform(put("/actors/update/lastname/{id}", id).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(actorDto)))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.lastName").value("Smith"));
//        verify(actorService, times(1)).updateActorLastName(id, updatedLastName);
    }

 

    @Test
    public void testUpdateActorFirstName() throws Exception {
        Short id = 1;
        String updatedFirstName = "Jack";
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName(updatedFirstName);
        ActorDto updatedActorDto = new ActorDto();
        updatedActorDto.setId(id);
        updatedActorDto.setFirstName(updatedFirstName);
        when(actorService.updateActorFirstName(id, updatedFirstName)).thenReturn(updatedActorDto);
        mockMvc.perform(put("/actors/update/firstname/{id}", id)
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(actorDto)))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstName").value("Jack"));
//        verify(actorService, times(1)).updateActorFirstName(id, updatedFirstName);
    }
    
    
    @Test
    public void testSearchFilmsByActorId() throws Exception {
        Short actorId = 12345;
        List<FilmDto> films = new ArrayList<>();
        Mockito.when(filmActorService.searchFilmsByActorId(actorId)).thenReturn(films);
        mockMvc.perform(MockMvcRequestBuilders.get("/actors/{id}/films", actorId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(films.size()));
        verify(filmActorService, Mockito.times(1)).searchFilmsByActorId(actorId);
    }
    
    
    
    @Test
    public void testAssignFilmToActor() throws Exception {
        Short actorId = 1;
        FilmDto filmDto = new FilmDto();
        filmDto.setFilmId((short) 1);

        FilmDto assignedFilmDto = new FilmDto();

        List<FilmDto> assignedFilms = Collections.singletonList(assignedFilmDto);
        when(filmActorService.assignFilmToActor(actorId, filmDto.getFilmId())).thenReturn(assignedFilms);

        mockMvc.perform(put("/actors/{id}/film", actorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk());        
               
    } 
    
    
    @Test
    public void testGetTopTenActorsByFilmCount() throws Exception {
        // Mock the response from the service
        CustomDto actor1 = new CustomDto((short) 1, "Actor 1", 10);
        CustomDto actor2 = new CustomDto((short) 2, "Actor 2", 8);
        List<CustomDto> actors = Arrays.asList(actor1, actor2);

        when(actorService.getTopTenActorsByFilmCount()).thenReturn(actors);

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/actors/toptenbyfilmcount")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert the response
        String responseJson = result.getResponse().getContentAsString();
        // Add your assertions here to validate the JSON response
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

