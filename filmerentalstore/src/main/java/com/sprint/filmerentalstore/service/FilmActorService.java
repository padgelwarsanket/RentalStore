package com.sprint.filmerentalstore.service;

import java.util.List;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.FilmActorDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.entity.FilmActor;


public interface FilmActorService {
	
	
	
	public FilmActorDto saveFilmActor(FilmActorDto filmActorDto );
	public List<ActorDto> getActorsByFilmId(Short filmId);
	public List<FilmDto> searchFilmsByActorId(Short actorId);
	public List<FilmDto> assignFilmToActor(Short actorId, Short filmId);
	
	
}
