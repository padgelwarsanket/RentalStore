package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.sprint.filmerentalstore.dto.FilmCategoryDto;
import com.sprint.filmerentalstore.service.FilmCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilmCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmCategoryService filmCategoryService;

    @Test
    public void testSaveFilmCategory() throws Exception {
        FilmCategoryDto filmCategoryDto = new FilmCategoryDto();
        // Set necessary properties for the filmCategoryDto

        when(filmCategoryService.saveFilmCategory(filmCategoryDto)).thenReturn(filmCategoryDto);

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmCategoryDto)))
                .andExpect(status().isOk());

        verify(filmCategoryService, times(1)).saveFilmCategory(filmCategoryDto);
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

