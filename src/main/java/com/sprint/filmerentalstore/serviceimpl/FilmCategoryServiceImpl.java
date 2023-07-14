package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.FilmCategoryDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.entity.Category;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.FilmCategory;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.CategoryRepository;
import com.sprint.filmerentalstore.repository.FilmCategoryRepository;
import com.sprint.filmerentalstore.repository.FilmRepository;
import com.sprint.filmerentalstore.service.FilmCategoryService;
import com.sprint.filmerentalstore.service.FilmService;

import jakarta.transaction.Transactional;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {

	@Autowired
	private FilmCategoryRepository filmCategoryRepository;

	@Autowired
	private FilmService filmService;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public FilmCategoryDto saveFilmCategory(FilmCategoryDto filmCategoryDto) {
		FilmCategory filmCategory = new FilmCategory();

		Optional<Film> film = filmRepository.findById(filmCategoryDto.getFilmId());
		if (!film.isPresent()) {
			throw new FilmRentalStoreException("FILM NOT FOUND");
		}
		Optional<Category> category = categoryRepository.findById(filmCategoryDto.getCategoryId());
		if (!category.isPresent()) {
			throw new FilmRentalStoreException("CATEGORY NOT FOUND");
		}

		filmCategory.setCategoryId(category.get());
		filmCategory.setFilmId(film.get());
		filmCategory.setLastUpdate(LocalDateTime.now());
		BeanUtils.copyProperties(filmCategoryDto, filmCategory);
		filmCategoryRepository.save(filmCategory);
		return filmCategoryDto;
	}

	@Override
	public List<FilmDto> getFilmsBycategoryId(Byte categoryId) {

		List<Film> searchFilmsByCategoryId = filmCategoryRepository.searchFilmsByCategory(categoryId);
		if(searchFilmsByCategoryId.isEmpty()) {
			throw new FilmRentalStoreException("FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : searchFilmsByCategoryId) {
			FilmDto filmDto = filmService.covertToFilmDto(film);
			dtos.add(filmDto);
		}
		return dtos;

	}

	@Override
	@Transactional
	public FilmDto updateFilmByCategoryId(Short filmId, Byte categoryDto) {
		int res = filmCategoryRepository.updateFilmByCategoryId(filmId, categoryDto);
		System.out.println(res);
		Film film = filmRepository.findById(filmId).get();
		/*
		 * Optional<Category> category = categoryRepository.findById(categoryDto);
		 * System.out.println(category.get()); FilmCategory filmCategory =
		 * filmCategoryRepository.getById(category.get().getCategoryId());
		 * System.out.println(filmCategory); Optional<Film> film =
		 * filmRepository.findById(filmCategory.getFilmId().getFilmId());
		 * System.out.println(film.get()); if (film.isPresent() && category.isPresent())
		 * { filmCategory.setCategoryId(category.get());
		 * filmCategory.setFilmId(film.get());
		 * filmCategoryRepository.save(filmCategory); FilmDto filmDto =
		 * filmService.covertToFilmDto(film.get()); return filmDto; }
		 */
		if (film != null) {
			film.setLastUpdate(LocalDateTime.now());
			FilmDto filmDto = filmService.covertToFilmDto(film);
			filmRepository.save(film);
			return filmDto;
		}
		throw new FilmRentalStoreException("FILM NOT FOUND");
	}

}
