package com.sprint.filmerentalstore.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sprint.filmerentalstore.dto.FilmCategoryDto;
import com.sprint.filmerentalstore.dto.FilmDto;




@Transactional
public interface FilmCategoryService {
	public List<FilmDto> getFilmsBycategoryId(Byte categoryId);
	public FilmDto updateFilmByCategoryId(Short filmId,Byte categoryDto);
	
	public FilmCategoryDto saveFilmCategory(FilmCategoryDto filmCategoryDto);

}
