package com.sprint.filmerentalstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.CategoryDto;
import com.sprint.filmerentalstore.dto.FilmCategoryDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.dto.LanguageDto;
import com.sprint.filmerentalstore.entity.Message;
import com.sprint.filmerentalstore.service.FilmActorService;
import com.sprint.filmerentalstore.service.FilmCategoryService;
import com.sprint.filmerentalstore.service.FilmService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private FilmActorService filmActorService;

	@Autowired
	private FilmCategoryService filmCategoryService;
	
	

	 //Create a new Film
	@PostMapping // http://localhost:9292/api/films
	public ResponseEntity<Message> createFilm(@RequestBody FilmDto filmdto) {
		System.out.println(filmdto);
		FilmDto createdFilm = filmService.createFilm(filmdto);
		if (createdFilm != null) {
			return ResponseEntity.ok(new Message("Record Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Failed to create Film"));
		}
	}

	// Get all Films
	@GetMapping("/all") // localhost:8082/api/films/all
	public ResponseEntity<List<FilmDto>> getAllFilms() {
		List<FilmDto> films = filmService.getAllFlims();
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by title
	@GetMapping("/title/{title}") // localhost:8082/api/films/title/{title}
	public ResponseEntity<List<FilmDto>> searchFilmsByTitle(@PathVariable String title) {
		List<FilmDto> films = filmService.searchFilmsByTitle(title);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Serach films by year
	@GetMapping("/year/{year}") // localhost:8082/api/films/year/{year}
	public ResponseEntity<List<FilmDto>> searchFilmsByReleaseYear(@PathVariable String year) {
		List<FilmDto> films = filmService.searchFilmsByYear(year);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by greater than rental duration
	@GetMapping("/duration/gt/{rentalDuration}") // localhost:8082/api/films/duration/gt/{rd}
	public ResponseEntity<List<FilmDto>> searchFilmsByRentalDurationGreaterThan(@PathVariable Integer rentalDuration) {
		List<FilmDto> films = filmService.searchFilmsByRentalDurationGreaterThan(rentalDuration);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by smaller than rental duration
	@GetMapping("/duration/lt/{rentalDuration}") // localhost:8082/api/films/duration/lt/{rd}
	public ResponseEntity<List<FilmDto>> searchFilmsByRentalDurationSmallerThan(@PathVariable Integer rentalDuration) {
		List<FilmDto> films = filmService.searchFilmsByRentalDurationLessThan(rentalDuration);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by greater than Rental Rate
	@GetMapping("/rate/gt/{rentalRate}") // localhost:8082/api/films/rate/gt/{rate}
	public ResponseEntity<List<FilmDto>> searchFilmsByRentalRateGreaterThan(@PathVariable Double rentalRate) {
		List<FilmDto> films = filmService.searchFilmsByRentalRateGreaterThan(rentalRate);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by smaller than rental Rate
	@GetMapping("/rate/lt/{rentalRate}")
	public ResponseEntity<List<FilmDto>> searchFilmsByRentalRateSmallerThan(@PathVariable Double rentalRate) {
		List<FilmDto> films = filmService.searchFilmsByRentalRateLessThan(rentalRate);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by greater than filmLength
	@GetMapping("/length/gt/{length}")
	public ResponseEntity<List<FilmDto>> searchFilmsByLengthGreaterThan(@PathVariable Integer length) {
		List<FilmDto> films = filmService.searchFilmsByLengthGreaterThan(length);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by smaller than filmLength
	@GetMapping("/length/lt/{length}")
	public ResponseEntity<List<FilmDto>> searchFilmsByLengthSmallerThan(@PathVariable Integer length) {
		List<FilmDto> films = filmService.searchFilmsByLengthLessThan(length);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by Between given the years
	@GetMapping("/betweenyear/{from}/{to}")
	public ResponseEntity<List<FilmDto>> serachFilmByBetweenFromAndTo(@PathVariable String from,
			@PathVariable String to) {
		List<FilmDto> films = filmService.searchFilmsReleasedBetweenYears(from, to);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by greater than rating
	@GetMapping("/rating/gt/{rating}")
	public ResponseEntity<List<FilmDto>> serachFilmByRatingGreaterThan(@PathVariable String rating) {
		List<FilmDto> films = filmService.searchFilmsByRatingGreaterThan(rating);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by smaller than rating
	@GetMapping("/rating/lt/{rating}")
	public ResponseEntity<List<FilmDto>> serachFilmByRatingSmallerThan(@PathVariable String rating) {
		List<FilmDto> films = filmService.searchFilmsByRatingLessThan(rating);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Search films by smaller than languageId
	@GetMapping("/language/{languageId}")
	public ResponseEntity<List<FilmDto>> serachFilmByRatingSmallerThan(@PathVariable Byte languageId) {
		List<FilmDto> films = filmService.searchFilmsByLanguage(languageId);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	// Get filmcount by year
	@GetMapping("/countbyyear")
	public ResponseEntity<Map<String, Long>> getFilmCountByYear() {
		Map<String, Long> filmCountByYear = filmService.getFilmCountByYear();
		return new ResponseEntity<>(filmCountByYear, HttpStatus.OK);
	}

	// ----------------------Update Controllers----------------------------
	@PutMapping("/update/title/{filmId}")
	public ResponseEntity<FilmDto> updateTitleByFilmId(@PathVariable Short filmId, @RequestBody FilmDto filmDto) {
		FilmDto updatedFilm = filmService.updateTitleByFilmId(filmId, filmDto.getTitle());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update/releaseyear/{filmId}")
	public ResponseEntity<FilmDto> updateReleaseYearByFilmId(@PathVariable Short filmId, @RequestBody FilmDto filmDto) {
		FilmDto updatedFilm = filmService.updateReleaseYearByFilmId(filmId, filmDto.getReleaseYear());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update/rentalduration/{filmId}")
	public ResponseEntity<FilmDto> updateRentalDurationByFilmId(@PathVariable Short filmId,
			@RequestBody FilmDto filmDto) {
		FilmDto updatedFilm = filmService.updateRentalDurationByFilmId(filmId, filmDto.getRentalDuration());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update/rentalrate/{filmId}")
	public ResponseEntity<FilmDto> updateRentalRateByFilmId(@PathVariable Short filmId, @RequestBody FilmDto filmDto) {
		FilmDto updatedFilm = filmService.updateRentalRateByFilmId(filmId, filmDto.getRentalRate());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update/rating/{filmId}")
	public ResponseEntity<FilmDto> updateRatingByFilmId(@PathVariable Short filmId, @RequestBody FilmDto filmDto) {
		FilmDto updatedFilm = filmService.updateRatingByFilmId(filmId, filmDto.getRating());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/update/language/{filmId}")
	public ResponseEntity<FilmDto> updateLanguageByFilmId(@PathVariable Short filmId,
			@RequestBody LanguageDto languageDto) {
		FilmDto updatedFilm = filmService.updateLanguageByFilmId(filmId, languageDto.getLanguageId());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	// ---------controllers for relationship between two classes--------------

	@GetMapping("/{filmId}/actors")
	public ResponseEntity<List<ActorDto>> getActorsByFilmId(@PathVariable("filmId") Short filmId) {
		List<ActorDto> actors = filmActorService.getActorsByFilmId(filmId);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<FilmDto>> searchFilmsByCatrgoryId(@PathVariable Byte categoryId) {
		List<FilmDto> films = filmCategoryService.getFilmsBycategoryId(categoryId);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	
	@PutMapping("/update/category/{filmId}")
	public ResponseEntity<FilmDto> updateCategoryByFilmId(@PathVariable Short filmId,@RequestBody CategoryDto categoryDto) {
		FilmDto updatedFilm = filmCategoryService.updateFilmByCategoryId(filmId,categoryDto.getCategoryId());
		if (updatedFilm != null) {
			return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/update/{id}")

    public ResponseEntity<FilmDto> updateFilm(@PathVariable("id") Short filmId,@Valid @RequestBody FilmDto filmDto){

        FilmDto updateFilm = filmService.updateFilm(filmId,filmDto);

        if (updateFilm != null) {

            return new ResponseEntity<>(updateFilm, HttpStatus.OK);

        }

        return ResponseEntity.notFound().build();

    }
	
	

}
