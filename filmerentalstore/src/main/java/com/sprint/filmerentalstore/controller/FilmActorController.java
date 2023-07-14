package com.sprint.filmerentalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.FilmActorDto;
import com.sprint.filmerentalstore.entity.Message;
import com.sprint.filmerentalstore.service.FilmActorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/filmactor")
public class FilmActorController {

	@Autowired
	FilmActorService filmActorService;

	@PostMapping
	public ResponseEntity<Message> saveFilmActor(@Valid @RequestBody FilmActorDto filmActorDto) {
		FilmActorDto saveFilmActor = filmActorService.saveFilmActor(filmActorDto);
		if (saveFilmActor != null) {
			return ResponseEntity.ok(new Message("Record Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Failed to create FilmActor"));
		}

	}

}
