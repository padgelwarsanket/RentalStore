package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.dto.LanguageDto;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.Language;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.FilmRepository;
import com.sprint.filmerentalstore.repository.LanguageRepository;
import com.sprint.filmerentalstore.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	public FilmRepository filmRepository;

	@Autowired
	public LanguageRepository languageRepository;

	@Override
	public FilmDto getByFilmId(Short filmId) {

		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isEmpty()) {
			throw new FilmRentalStoreException("FILM NOT FOUND");
		}
		FilmDto filmdto = new FilmDto();
		BeanUtils.copyProperties(findById, filmdto);
		return filmdto;

	}

	// ---------------------for update service imlementation
	// methods-----------------------
	@Override
	public FilmDto updateTitleByFilmId(Short filmId, String title) {
		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isPresent()) {
			Film film = findById.get();
			film.setTitle(title);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");

	}

	@Override
	public FilmDto updateReleaseYearByFilmId(Short filmId, String releaseYear) {
		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isPresent()) {
			Film film = findById.get();
			film.setReleaseYear(releaseYear);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

	@Override
	public FilmDto updateRentalDurationByFilmId(Short filmId, Integer rentalDuration) {
		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isPresent()) {
			Film film = findById.get();
			film.setRentalDuration(rentalDuration);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

	@Override
	public FilmDto updateRentalRateByFilmId(Short filmId, Double rentalRate) {
		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isPresent()) {
			Film film = findById.get();
			film.setRentalRate(rentalRate);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

	@Override
	public FilmDto updateRatingByFilmId(Short filmId, String rating) {
		Optional<Film> findById = filmRepository.findById(filmId);
		if (findById.isPresent()) {
			Film film = findById.get();
			film.setRating(rating);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

	@Override
	public FilmDto updateLanguageByFilmId(Short filmId, Byte languageId) {
		Optional<Film> optionalfilm = filmRepository.findById(filmId);
		Language language = languageRepository.findById(languageId).orElse(null);
		if (optionalfilm.isPresent()) {
			Film film = optionalfilm.get();
			film.setLanguage(language);
			film.setLastUpdate(LocalDateTime.now());
			filmRepository.save(film);
			FilmDto filmDto = covertToFilmDto(film);
			return filmDto;

		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

	@Override
	public FilmDto covertToFilmDto(Film film) {
		FilmDto filmDto = new FilmDto();
		filmDto.setFilmId(film.getFilmId());
		filmDto.setDescription(film.getDescription());
		filmDto.setLength(film.getLength());
		filmDto.setOriginalLanguageId(film.getOriginalLanguage());
		filmDto.setRating(film.getRating());
		filmDto.setReleaseYear(film.getReleaseYear());
		filmDto.setRentalDuration(film.getRentalDuration());
		filmDto.setRentalRate(film.getRentalRate());
		filmDto.setReplacementCost(film.getReplacementCost());
		filmDto.setSpecialFeatures(film.getSpecialFeatures());
		filmDto.setTitle(film.getTitle());
		filmDto.setLastUpdate(film.getLastUpdate());
		filmDto.setLanguageId(film.getLanguage().getLanguageId());
		return filmDto;
	}

	@Override
	public Film convertToFilmEntity(FilmDto filmDto) {
		Film film = new Film();
		film.setFilmId(filmDto.getFilmId());
		film.setDescription(filmDto.getDescription());
		film.setLength(filmDto.getLength());
		film.setOriginalLanguage(filmDto.getOriginalLanguageId());
		film.setRating(filmDto.getRating());
		film.setReleaseYear(filmDto.getReleaseYear());
		film.setRentalDuration(filmDto.getRentalDuration());
		film.setRentalRate(filmDto.getRentalRate());
		film.setReplacementCost(filmDto.getReplacementCost());
		film.setSpecialFeatures(filmDto.getSpecialFeatures());
		film.setTitle(filmDto.getTitle());
		film.setLastUpdate(filmDto.getLastUpdate());
		Language language = languageRepository.findById(filmDto.getLanguageId()).orElse(null);
		film.setLanguage(language);
		return film;
	}

	@Override
	public FilmDto createFilm(FilmDto filmDto) {
		Film film = convertToFilmEntity(filmDto);
		film.setLastUpdate(LocalDateTime.now());
		filmRepository.save(film);
		return filmDto;
	}

	@Override
	public List<FilmDto> getAllFlims() {
		List<Film> films = filmRepository.findAll();
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByTitle(String title) {
		List<Film> films = filmRepository.findByTitle(title);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByYear(String releaseYear) {
		List<Film> films = filmRepository.findByReleaseYear(releaseYear);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRentalDurationGreaterThan(Integer rentalDuration) {

		List<Film> films = filmRepository.findByRentalDurationGreaterThan(rentalDuration);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRentalRateGreaterThan(Double rentalRate) {
		List<Film> films = filmRepository.findByRentalRateGreaterThan(rentalRate);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRentalDurationLessThan(Integer rentalDuration) {
		List<Film> films = filmRepository.findByRentalDurationSmallerThan(rentalDuration);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRentalRateLessThan(Double rentalRate) {
		List<Film> films = filmRepository.findByRentalRateSmallerThan(rentalRate);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByLengthGreaterThan(Integer length) {
		List<Film> films = filmRepository.findByLengthGreaterThan(length);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByLengthLessThan(Integer length) {
		List<Film> films = filmRepository.findByLengthSmallerThan(length);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsReleasedBetweenYears(String from, String to) {
		List<Film> films = filmRepository.findByReleaseYearBetween(from, to);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRatingLessThan(String rating) {
		List<Film> films = filmRepository.findByRatingGreaterThan(rating);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> searchFilmsByRatingGreaterThan(String rating) {
		List<Film> films = filmRepository.findByRatingSmallerThan(rating);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public Map<String, Long> getFilmCountByYear() {
		List<Object[]> results = filmRepository.findFilmCountByYear();
		Map<String, Long> filmCountByYear = new LinkedHashMap<>();
		for (Object[] result : results) {
			String year = (String) result[0];
			Long count = (Long) result[1];
			filmCountByYear.put(year, count);
		}
		return filmCountByYear;
	}

	@Override
	public List<FilmDto> searchFilmsByLanguage(Byte languageId) {
		List<Film> films = filmRepository.findByLanguageId(languageId);
		if (films.isEmpty()) {
			throw new FilmRentalStoreException("LIST OF FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;
	}

	@Override
	public FilmDto updateFilm(Short filmId, FilmDto filmDto) {

		// Film film = new Film();

		Optional<Film> film1 = filmRepository.findById(filmId);

		Film film = film1.get();

		// Film =convertToFilmEntity2(filmDto);

		film.setTitle(filmDto.getTitle());

		film.setRating(filmDto.getRating());

		film.setReleaseYear(filmDto.getReleaseYear());

		film.setRentalDuration(filmDto.getRentalDuration());

		film.setRentalRate(filmDto.getRentalRate());

		Language language = languageRepository.findById(filmDto.getLanguageId()).orElse(null);

		film.setLanguage(language);
		// remainnig values settting
//		film.setDescription(film1.get().getDescription());
//		film.setLastUpdate(film1.get().getLastUpdate());
//		film.setLength(film1.get().getLength());
//		// film.getOriginalLanguage(film)
//		film.setReplacementCost(film1.get().getReplacementCost());
//		film.setSpecialFeatures(film1.get().getSpecialFeatures());
		film.setLastUpdate(LocalDateTime.now());
		Film save = filmRepository.save(film);

		filmDto.setFilmId(filmId);
		FilmDto dto = covertToFilmDto(save);
		
		return dto;

	}
}
