package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.sprint.filmerentalstore.dto.FilmActorDto;
import com.sprint.filmerentalstore.service.FilmActorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilmActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmActorService filmActorService;

    @Test
    public void testSaveFilmActor() throws Exception {
        FilmActorDto filmActorDto = new FilmActorDto();
        // Set the necessary properties for filmActorDto

        when(filmActorService.saveFilmActor(filmActorDto)).thenReturn(filmActorDto);

        mockMvc.perform(post("/filmactor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmActorDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Created Successfully"));

        verify(filmActorService, times(1)).saveFilmActor(filmActorDto);
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

