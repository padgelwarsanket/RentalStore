package com.sprint.filmerentalstore.service;

import java.util.List;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.CustomDto;


public interface ActorService {

	    ActorDto createActor(ActorDto actorDto);
	   
	    ActorDto getByactorId(Short id);
	    List<ActorDto> searchActorsByLastName(String lastName);
	    
	    List<ActorDto> searchActorsByFirstName(String firstName);

		List<ActorDto> searchAll();

		ActorDto updateActorLastName(Short id, String lastName);

		ActorDto updateActorFirstName(Short id, String firstName);

		//List<CustomDto> findTopTenActorsByFilmCount();

		List<CustomDto> getTopTenActorsByFilmCount();

		
	    
//	    ActorDto updateActorLastName(Long id, String lastName);
//	    
//	    ActorDto updateActorFirstName(Long id, String firstName);
	    
	   // List<FilmDto> searchFilmsByActorId(Long actorId);
	    
	   // FilmDto assignFilmToActor(Long actorId, FilmDto filmDto);
	    
	   // List<ActorDto> findTopTenActorsByFilmCount();
	    
	    
	    
	    
}
