package com.sprint.filmerentalstore.service;

import java.util.List;
import java.util.Map;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.dto.LanguageDto;
import com.sprint.filmerentalstore.entity.Actor;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.Language;

public interface FilmService {

	FilmDto covertToFilmDto(Film film);

	Film convertToFilmEntity(FilmDto filmDto);

	List<FilmDto> getAllFlims();

	FilmDto getByFilmId(Short filmId);

	FilmDto createFilm(FilmDto film);

	List<FilmDto> searchFilmsByTitle(String title);

	List<FilmDto> searchFilmsByYear(String releaseYear);

	List<FilmDto> searchFilmsByRentalDurationGreaterThan(Integer rentalDuration);

	List<FilmDto> searchFilmsByRentalRateGreaterThan(Double rentalRate);

	List<FilmDto> searchFilmsByLengthGreaterThan(Integer length);

	List<FilmDto> searchFilmsByRentalDurationLessThan(Integer rentalDuration);

	List<FilmDto> searchFilmsByRentalRateLessThan(Double rentalRate);

	List<FilmDto> searchFilmsByLengthLessThan(Integer length);

	List<FilmDto> searchFilmsReleasedBetweenYears(String from, String to);

	List<FilmDto> searchFilmsByRatingLessThan(String rating);

	List<FilmDto> searchFilmsByRatingGreaterThan(String rating);

	List<FilmDto> searchFilmsByLanguage(Byte languageId);

	Map<String, Long> getFilmCountByYear();

	FilmDto updateTitleByFilmId(Short filmId, String title);

	FilmDto updateReleaseYearByFilmId(Short filmId, String releaseYear);

	FilmDto updateRentalDurationByFilmId(Short filmId, Integer rentalDuration);

	FilmDto updateRentalRateByFilmId(Short filmId, Double rentalRate);

	FilmDto updateRatingByFilmId(Short filmId, String rating);

	FilmDto updateLanguageByFilmId(Short filmId, Byte languageId);

	FilmDto updateFilm(Short filmId, FilmDto filmDto);

}
