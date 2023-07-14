package com.sprint.filmerentalstore.controller.positive;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.CategoryDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.dto.LanguageDto;
import com.sprint.filmerentalstore.entity.Category;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.FilmCategory;
import com.sprint.filmerentalstore.service.FilmActorService;
import com.sprint.filmerentalstore.service.FilmCategoryService;
import com.sprint.filmerentalstore.service.FilmService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerTest {

	 @MockBean
		private FilmCategoryService film;
	 
	 @MockBean
	 private FilmService filmservice;
	 
	 @MockBean
	 private FilmActorService filmactor;
	    
	    @Autowired
	    private MockMvc mockMvc;
	    
	    
	    @Test
	    public void testCreateFilm() throws Exception {
	        FilmDto filmDto = new FilmDto();
	        doReturn(filmDto).when(filmservice).createFilm(filmDto);
	        ResultActions resultActions = mockMvc.perform(post("/films")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isOk());
	        verify(filmservice).createFilm(filmDto);
	        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
	        assert responseContent.equals("Record Created Successfully");
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
    void testGetAllFilms() throws Exception {
        List<FilmDto> films = Arrays.asList(new FilmDto(), new FilmDto());
        when(filmservice.getAllFlims()).thenReturn(films);
        mockMvc.perform(MockMvcRequestBuilders.get("/films/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(films.size()));
    }

    @Test
    void testSearchFilmsByTitle() throws Exception {

        String title = "Example Title";
        List<FilmDto> films = Arrays.asList(new FilmDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/films/title/{title}", title))
                .andExpect(status().isOk());
    }
    
    
    @Test
    public void testSearchFilmsByReleaseYear() throws Exception {
        String year = "2021";
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByYear(year);
        mockMvc.perform(get("/films/year/{year}", year)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByYear(year);
    }
    
    @Test
    public void testSearchFilmsByRentalDurationGreaterThan() throws Exception {
        // Prepare test data
        int rentalDuration = 2;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRentalDurationGreaterThan(rentalDuration);
        mockMvc.perform(get("/films/duration/gt/{rentalDuration}", rentalDuration)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRentalDurationGreaterThan(rentalDuration);
    }
    
    
    
    @Test
    public void testSearchFilmsByRentalDurationSmallerThan() throws Exception {
        int rentalDuration = 5;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRentalDurationLessThan(rentalDuration);
        mockMvc.perform(get("/films/duration/lt/{rentalDuration}", rentalDuration)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRentalDurationLessThan(rentalDuration);
    }
    
    @Test
    public void testSearchFilmsByRentalRateGreaterThan() throws Exception {
        // Prepare test data
        double rentalRate = 4.5;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRentalRateGreaterThan(rentalRate);
        mockMvc.perform(get("/films/rate/gt/{rentalRate}", rentalRate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRentalRateGreaterThan(rentalRate);
    }
    
    
    
    @Test
    public void testSearchFilmsByRentalRateSmallerThan() throws Exception {
        double rentalRate = 4.5;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRentalRateLessThan(rentalRate);
        mockMvc.perform(get("/films/rate/lt/{rentalRate}", rentalRate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRentalRateLessThan(rentalRate);
    }
    
    
    @Test
    public void testSearchFilmsByLengthGreaterThan() throws Exception {
        // Prepare test data
        int length = 120;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByLengthGreaterThan(length);
        mockMvc.perform(get("/films/length/gt/{length}", length)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByLengthGreaterThan(length);
    }

    
    @Test
    public void testSearchFilmsByLengthSmallerThan() throws Exception {
        int length = 120;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByLengthLessThan(length);
        mockMvc.perform(get("/films/length/lt/{length}", length)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByLengthLessThan(length);
    }
    
    
    
    @Test
    public void testSerachFilmByBetweenFromAndTo() throws Exception {
        String fromYear = "2010";
        String toYear = "2020";
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsReleasedBetweenYears(fromYear, toYear);
        mockMvc.perform(get("/films/betweenyear/{from}/{to}", fromYear, toYear)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsReleasedBetweenYears(fromYear, toYear);
    }
    
    
    
    
    @Test
    public void testSerachFilmByRatingGreaterThan() throws Exception {
        String rating = "PG-13";
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRatingGreaterThan(rating);
        mockMvc.perform(get("/films/rating/gt/{rating}", rating)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRatingGreaterThan(rating);
    }
    
    
    
    
    @Test
    public void testSerachFilmByRatingSmallerThan() throws Exception {
        // Prepare test data
        String rating = "PG-13";
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByRatingLessThan(rating);
        mockMvc.perform(get("/films/rating/lt/{rating}", rating)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByRatingLessThan(rating);
    }

    @Test
    public void testSerachFilmByLanguage() throws Exception {
        byte languageId = 1;
        FilmDto film1 = new FilmDto();
        film1.setTitle("Film 1");
        FilmDto film2 = new FilmDto();
        film2.setTitle("Film 2");
        List<FilmDto> films = Arrays.asList(film1, film2);
        doReturn(films).when(filmservice).searchFilmsByLanguage(languageId);
        mockMvc.perform(get("/films/language/{languageId}", languageId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Film 1"))
                .andExpect(jsonPath("$[1].title").value("Film 2"));
        verify(filmservice).searchFilmsByLanguage(languageId);
    }

    @Test
    public void testGetFilmCountByYear() throws Exception {
        Map<String, Long> filmCountByYear = new HashMap<>();
        filmCountByYear.put("2020", 10L);
        filmCountByYear.put("2021", 15L);
        doReturn(filmCountByYear).when(filmservice).getFilmCountByYear();
        mockMvc.perform(get("/films/countbyyear")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.2020").value(10))
                .andExpect(jsonPath("$.2021").value(15));

        // Verify that the service method was called
        verify(filmservice).getFilmCountByYear();
    }
    
    //______________________________________Update_________________________________________
     
    
    
    @Test
    public void testUpdateTitleByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        String newTitle = "New Film Title";
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle(newTitle);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);
        updatedFilm.setTitle(newTitle);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateTitleByFilmId(filmId, newTitle);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/title/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value((int)filmId))
                .andExpect(jsonPath("$.title").value(newTitle));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateTitleByFilmId(filmId, newTitle);
    }

    
    
    public void testUpdateReleaseYearByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        String newReleaseYear = "2023";
        FilmDto filmDto = new FilmDto();
        filmDto.setReleaseYear(newReleaseYear);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);
        updatedFilm.setReleaseYear(newReleaseYear);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateReleaseYearByFilmId(filmId, newReleaseYear);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/releaseyear/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value(filmId))
                .andExpect(jsonPath("$.releaseYear").value(newReleaseYear));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateReleaseYearByFilmId(filmId, newReleaseYear);
    }

    @Test
    public void testUpdateRentalDurationByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        Integer newRentalDuration = 7;
        FilmDto filmDto = new FilmDto();
        filmDto.setRentalDuration(newRentalDuration);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);
        updatedFilm.setRentalDuration(newRentalDuration);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateRentalDurationByFilmId(filmId, newRentalDuration);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/rentalduration/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value((int)filmId))
                .andExpect(jsonPath("$.rentalDuration").value(newRentalDuration));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateRentalDurationByFilmId(filmId, newRentalDuration);
    }

    
    
    
    @Test
    public void testUpdateRentalRateByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        Double newRentalRate = 9.99;
        FilmDto filmDto = new FilmDto();
        filmDto.setRentalRate(newRentalRate);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);
        updatedFilm.setRentalRate(newRentalRate);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateRentalRateByFilmId(filmId, newRentalRate);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/rentalrate/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value((int)filmId))
                .andExpect(jsonPath("$.rentalRate").value(newRentalRate));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateRentalRateByFilmId(filmId, newRentalRate);
    }
    
    
    @Test
    public void testUpdateRatingByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        String newRating = "PG";
        FilmDto filmDto = new FilmDto();
        filmDto.setRating(newRating);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);
        updatedFilm.setRating(newRating);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateRatingByFilmId(filmId, newRating);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/rating/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value((int)filmId))
                .andExpect(jsonPath("$.rating").value(newRating));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateRatingByFilmId(filmId, newRating);
    }

    @Test
    public void testUpdateLanguageByFilmId() throws Exception {
        // Prepare test data
        Short filmId = 1;
        Byte newLanguageId = 1;
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageId(newLanguageId);
        FilmDto updatedFilm = new FilmDto();
        updatedFilm.setFilmId(filmId);

        // Mock the service method
        doReturn(updatedFilm).when(filmservice).updateLanguageByFilmId(filmId, newLanguageId);

        // Perform the PUT request
        mockMvc.perform(put("/films/update/language/{filmId}", filmId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(languageDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value((int)filmId));

        // Verify that the service method was called with the correct arguments
        verify(filmservice).updateLanguageByFilmId(filmId, newLanguageId);
    }
    
   // ______________________________________add_______________________________________
    
    
    @Test
    public void testGetActorsByFilmId() throws Exception {
        Short filmId = 1;
        List<ActorDto> actors = Arrays.asList(new ActorDto(), new ActorDto());
        doReturn(actors).when(filmactor).getActorsByFilmId(filmId);
        mockMvc.perform(get("/films/{filmId}/actors", filmId))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchFilmsByCategoryId() throws Exception {
        Byte categoryId = 1;
        List<ActorDto> films = Arrays.asList(new ActorDto(), new ActorDto());
        doReturn(films).when(film).getFilmsBycategoryId(categoryId);
        mockMvc.perform(get("/films/category/{categoryId}", categoryId))
                .andExpect(status().isOk());
  
    }
    
    
//    @Test
//    public void testUpdateCategoryByFilmId() throws Exception {
//        Short filmId = 1;
//        Byte categoryId = 2;
//        Film updated = new Film();
//        FilmCategory filmcatogory=new FilmCategory();
//        Category category = new Category();
//        category.setCategoryId(categoryId);
//        //updated.setFilmId(filmId);
//        filmcatogory.setCategoryId(category);
//        updated.setFilmId(filmId);
//        //updated.set
//        //updatedFilm.set//setCategoryId(categoryId);
//
//        // Mock the service method
//        when(film.updateFilmByCategoryId(filmId, categoryId)).thenReturn(updatedFilm);
//
//        // Perform the PUT request
//        mockMvc.perform(put("/update/category/{filmId}", filmId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(categoryDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.filmId").value(filmId))
//                .andExpect(jsonPath("$.categoryId").value(categoryId));
//
//        // Verify that the service method was called with the correct arguments
//        verify(film).updateFilmByCategoryId(filmId, categoryId);
//    }


}

