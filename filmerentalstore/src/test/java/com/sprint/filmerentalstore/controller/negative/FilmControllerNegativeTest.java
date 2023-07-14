package com.sprint.filmerentalstore.controller.negative;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.sprint.filmerentalstore.dto.CategoryDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.dto.LanguageDto;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.FilmActorService;
import com.sprint.filmerentalstore.service.FilmCategoryService;
import com.sprint.filmerentalstore.service.FilmService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerNegativeTest {

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
	        // Prepare test data
	        FilmDto filmDto = new FilmDto();

	        // Mock the service method to return null, indicating failure
	        doReturn(null).when(filmservice).createFilm(filmDto);

	        // Perform the POST request
	        mockMvc.perform(post("/films")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isInternalServerError());
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
	    	
	        when(filmservice.getAllFlims()).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/all"))
	                .andExpect(status().isNotFound());
	    }
	    
	    
	    @Test
	    void testSearchFilmsByTitle_NotFound() throws Exception {
	        String title = "Nonexistent Film";
	        when(filmservice.searchFilmsByTitle(title)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/title/{title}", title))
	                .andExpect(status().isNotFound());
	    }



	    
	    @Test
	    void testSearchFilmsByReleaseYear_NotFound() throws Exception {
	        String year = "2025";
	        when(filmservice.searchFilmsByYear(year)).thenThrow(new FilmRentalStoreException("not found"));

	        // Perform the GET request
	        mockMvc.perform(get("/films/year/{year}", year))
	                .andExpect(status().isNotFound());
	    }


	    
	    
	    @Test
	    void testSearchFilmsByRentalDurationGreaterThan_NotFound() throws Exception {
	        int rentalDuration = 5;
	        when(filmservice.searchFilmsByRentalDurationGreaterThan(rentalDuration)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/duration/gt/{rentalDuration}", rentalDuration))
	                .andExpect(status().isNotFound());
	    }


	    
	    @Test
	    public void testSearchFilmsByRentalDurationSmallerThan() throws Exception {
	        int rentalDuration = 5;
	        when(filmservice.searchFilmsByRentalDurationLessThan(rentalDuration)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/duration/lt/{rentalDuration}", rentalDuration))
	        .andExpect(status().isNotFound());
	    }


	    
	    @Test
	    void testSearchFilmsByRentalRateGreaterThan_NotFound() throws Exception {
	        double rentalRate = 4.5;

	        // Mock the filmService.searchFilmsByRentalRateGreaterThan() method to return an empty list
	        when(filmservice.searchFilmsByRentalRateGreaterThan(rentalRate)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/rate/gt/{rentalRate}", rentalRate))
	                .andExpect(status().isNotFound());
	    }


	    
	    @Test
	    public void testSearchFilmsByRentalRateSmallerThan() throws Exception {
	        double rentalRate = 4.5;
	        when(filmservice.searchFilmsByRentalRateLessThan(rentalRate)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/rate/lt/{rentalRate}", rentalRate))
	                .andExpect(status().isNotFound());
	    }

	    
	    @Test
	    public void testSearchFilmsByLengthLessThan() throws Exception {
	        int length = 120;
	        when(filmservice.searchFilmsByLengthLessThan(length)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/length/lt/{length}", length))
	                .andExpect(status().isNotFound());
	    }

	    
	    @Test
	    void testSearchFilmsByLengthGreaterThan_NotFound() throws Exception {
	        int length = 120;
	        when(filmservice.searchFilmsByLengthGreaterThan(length)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/length/gt/{length}", length))
	                .andExpect(status().isNotFound());
	    }


	    
	    @Test
	    void testSerachFilmByBetweenFromAndTo_NotFound() throws Exception {
	        String fromYear = "2010";
	        String toYear = "2022";
	        when(filmservice.searchFilmsReleasedBetweenYears(fromYear, toYear)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/betweenyear/{from}/{to}", fromYear, toYear))
	                .andExpect(status().isNotFound());
	    }


	    
	    
	    @Test
	    void testSerachFilmByRatingGreaterThan_NotFound() throws Exception {
	        String rating = "R";
	        // Mock the filmService.searchFilmsByRatingGreaterThan() method to return an empty list
	        when(filmservice.searchFilmsByRatingGreaterThan(rating)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/rating/gt/{rating}", rating))
	                .andExpect(status().isNotFound());
	    }


	    
	    @Test
	    public void testSerachFilmByRatingSmallerThan() throws Exception {
	        String rating = "PG-13";
	        when(filmservice.searchFilmsByRatingLessThan(rating)).thenThrow(new FilmRentalStoreException("not found"));
	        mockMvc.perform(get("/films/rating/lt/{rating}", rating))
	                .andExpect(status().isNotFound());
	    }
	    
	    @Test
	    void testSerachFilmByLanguage_NotFound() throws Exception {
	        Byte languageId = 1;
	        when(filmservice.searchFilmsByLanguage(languageId)).thenThrow(new FilmRentalStoreException("not Found"));
	        mockMvc.perform(get("/films/language/{languageId}", languageId))
	                .andExpect(status().isNotFound());
	    }

	    @Test
	    void testGetFilmCountByYear_NotFound() throws Exception {
	        when(filmservice.getFilmCountByYear()).thenThrow(new FilmRentalStoreException("not Found"));
	        mockMvc.perform(get("/films/countbyyear"))
	                .andExpect(status().isNotFound());
	    }


	 //-------------------------------------------update----------------------------------------   
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

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateTitleByFilmId(filmId, newTitle);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/title/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateTitleByFilmId(filmId, newTitle);
	    }




	    @Test
	    public void testUpdateReleaseYearByFilmId() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        String newReleaseYear = "2023";
	        FilmDto filmDto = new FilmDto();
	        filmDto.setReleaseYear(newReleaseYear);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateReleaseYearByFilmId(filmId, newReleaseYear);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/releaseyear/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateReleaseYearByFilmId(filmId, newReleaseYear);
	    }
	    
	    
	    @Test
	    public void testUpdateRentalDurationByFilmId_Negative() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        Integer newRentalDuration = 7;
	        FilmDto filmDto = new FilmDto();
	        filmDto.setRentalDuration(newRentalDuration);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateRentalDurationByFilmId(filmId, newRentalDuration);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/rentalduration/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateRentalDurationByFilmId(filmId, newRentalDuration);
	    }

	    
	    @Test
	    public void testUpdateRentalRateByFilmId_Negative() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        Double newRentalRate = 9.99;
	        FilmDto filmDto = new FilmDto();
	        filmDto.setRentalRate(newRentalRate);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateRentalRateByFilmId(filmId, newRentalRate);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/rentalrate/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateRentalRateByFilmId(filmId, newRentalRate);
	    }
	    
	    @Test
	    public void testUpdateRatingByFilmId_Negative() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        String newRating = "PG";
	        FilmDto filmDto = new FilmDto();
	        filmDto.setRating(newRating);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateRatingByFilmId(filmId, newRating);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/rating/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(filmDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateRatingByFilmId(filmId, newRating);
	    }

	    
	    @Test
	    public void testUpdateLanguageByFilmId_Negative() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        Byte newLanguageId = 1;
	        LanguageDto languageDto = new LanguageDto();
	        languageDto.setLanguageId(newLanguageId);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        doReturn(null).when(filmservice).updateLanguageByFilmId(filmId, newLanguageId);

	        // Perform the PUT request
	        mockMvc.perform(put("/films/update/language/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(languageDto)))
	                .andExpect(status().isNotFound());

	        // Verify that the service method was called with the correct arguments
	        verify(filmservice).updateLanguageByFilmId(filmId, newLanguageId);
	    }
	    //-----------------------------add------------------------
	    
	    
	    
	    
	    @Test
	    void testGetActorsByFilmId_NotFound() throws Exception {
	        Short filmId = 1;
	        when(filmactor.getActorsByFilmId(filmId)).thenReturn(null);
	        mockMvc.perform(get("/{filmId}/actors", filmId))
	                .andExpect(status().isNotFound());
	    }


	    @Test
	    void testSearchFilmsByCategoryId_NotFound() throws Exception {
	        Byte categoryId = 1;
	        when(film.getFilmsBycategoryId(categoryId)).thenReturn(null);
	        mockMvc.perform(get("/category/{categoryId}", categoryId))
	                .andExpect(status().isNotFound());
	    }
	    
	    @Test
	    void testUpdateCategoryByFilmId_NotFound() throws Exception {
	        // Prepare test data
	        Short filmId = 1;
	        CategoryDto categoryDto = new CategoryDto();
	        categoryDto.setCategoryId((byte)2);

	        // Mock the service method to return null, indicating the update was unsuccessful
	        when(film.updateFilmByCategoryId(filmId, categoryDto.getCategoryId())).thenReturn(null);

	        // Perform the PUT request
	        mockMvc.perform(put("/update/category/{filmId}", filmId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(categoryDto)))
	                .andExpect(status().isNotFound());
	    }



}
	    












