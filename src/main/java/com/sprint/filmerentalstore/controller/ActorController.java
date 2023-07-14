package com.sprint.filmerentalstore.controller;

import java.util.List;

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
import com.sprint.filmerentalstore.dto.CustomDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.entity.Message;
import com.sprint.filmerentalstore.service.ActorService;
import com.sprint.filmerentalstore.service.FilmActorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	private ActorService actorService;
//    
	@Autowired
	private FilmActorService filmActorService;

	@PostMapping // http://localhost:9292//api/actors/post
	public ResponseEntity<Message> createActor(@RequestBody ActorDto actorDto) {
		ActorDto createdActor = actorService.createActor(actorDto);
		if (createdActor != null) {
			return ResponseEntity.ok(new Message("Record Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Failed"));
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<ActorDto>> searchAll() {
		List<ActorDto> actors = actorService.searchAll();

		return new ResponseEntity<>(actors, HttpStatus.OK);

	}

	@GetMapping("/lastname/{lastName}") // http://localhost:9292//api/actors/lastname/{ln}
	public ResponseEntity<List<ActorDto>> searchActorsByLastName(@PathVariable String lastName) {
		List<ActorDto> actors = actorService.searchActorsByLastName(lastName);

		return new ResponseEntity<>(actors, HttpStatus.OK);

	}

	@GetMapping("/firstname/{firstName}") // http://localhost:9292//api/actors/fisrtname/{fn}
	public ResponseEntity<List<ActorDto>> searchActorsByFirstName(@PathVariable String firstName) {
		List<ActorDto> actors = actorService.searchActorsByFirstName(firstName);

		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@PutMapping("/update/lastname/{id}")
	public ResponseEntity<ActorDto> updateActorLastName(@Valid @PathVariable("id") Short id, @RequestBody ActorDto actorDto) {
		ActorDto updatedActor = actorService.updateActorLastName(id, actorDto.getLastName());
		return ResponseEntity.ok(updatedActor);
	}


	@PutMapping("/update/firstname/{id}")
	public ResponseEntity<ActorDto> updateActorFirstName(@Valid @PathVariable("id") Short id, @RequestBody ActorDto actorDto) {
		ActorDto updatedActor = actorService.updateActorFirstName(id, actorDto.getFirstName());
		return ResponseEntity.ok(updatedActor);
	}

	@GetMapping("/{id}/films")
	public ResponseEntity<List<FilmDto>> searchFilmsByActorId(@PathVariable("id") Short actorId) {
		List<FilmDto> films = filmActorService.searchFilmsByActorId(actorId);
		return ResponseEntity.ok(films);
	}

	@PutMapping("/{id}/film")
	public ResponseEntity<List<FilmDto>> assignFilmToActor(@Valid @PathVariable("id") Short actorId,
			@RequestBody FilmDto filmDto) {
		List<FilmDto> assignFilmToActor = filmActorService.assignFilmToActor(actorId, filmDto.getFilmId());
		return new ResponseEntity<>(assignFilmToActor, HttpStatus.OK);
	}
//    

	@GetMapping("/toptenbyfilmcount")
	public ResponseEntity<List<CustomDto>> getTopTenActorsByFilmCount() {
		List<CustomDto> topActors = actorService.getTopTenActorsByFilmCount();
		return ResponseEntity.ok(topActors);
	}

}
