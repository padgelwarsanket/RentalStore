package com.sprint.filmerentalstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.FilmCategory;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Byte> {
//
@Query("SELECT fc.filmId FROM FilmCategory fc WHERE fc.categoryId.categoryId = :categoryId")
List<Film> searchFilmsByCategory(Byte categoryId);
	
	public FilmCategory getById(Byte id);

	
	  @Modifying
	  @Query("UPDATE FilmCategory fc SET fc.categoryId.categoryId=:categoryId WHERE fc.filmId.filmId=:filmId") 
	  int updateFilmByCategoryId(Short filmId, Byte categoryId);
	 	
	
	
}
