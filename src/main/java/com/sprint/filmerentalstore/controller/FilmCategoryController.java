package com.sprint.filmerentalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.FilmCategoryDto;
import com.sprint.filmerentalstore.entity.Message;
import com.sprint.filmerentalstore.service.FilmCategoryService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
public class FilmCategoryController {

	@Autowired
	FilmCategoryService filmCategoryService;

	@PostMapping // http://localhost:9292/api/category
	public ResponseEntity<Message> saveFilmCategory(@Valid @RequestBody FilmCategoryDto filmCategoryDto) {
		FilmCategoryDto filmCategory = filmCategoryService.saveFilmCategory(filmCategoryDto);
		if (filmCategory != null) {
			return ResponseEntity.ok(new Message("Record Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Failed to create FilmCategory"));
		}
	}

}
