package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.FilmActorDto;
import com.sprint.filmerentalstore.dto.FilmDto;
import com.sprint.filmerentalstore.entity.Actor;
import com.sprint.filmerentalstore.entity.Category;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.FilmActor;
import com.sprint.filmerentalstore.entity.FilmCategory;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.ActorRepository;
import com.sprint.filmerentalstore.repository.FilmActorRepository;
import com.sprint.filmerentalstore.repository.FilmRepository;
import com.sprint.filmerentalstore.service.FilmActorService;
import com.sprint.filmerentalstore.service.FilmService;

@Service
public class FilmActorServiceImpl implements FilmActorService {

	@Autowired
	private FilmActorRepository filmActorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private FilmService filmService;

	@Autowired
	private ActorRepository actorRepository;

	@Override
	public FilmActorDto saveFilmActor(FilmActorDto filmActorDto) {
		FilmActor filmActor = new FilmActor();

		Optional<Film> film = filmRepository.findById(filmActorDto.getFilmId());
		if (!film.isPresent()) {
			throw new FilmRentalStoreException("FILM NOT FOUND");
		}
		Optional<Actor> actor = actorRepository.findById(filmActorDto.getActorId());
		if (!actor.isPresent()) {
			throw new FilmRentalStoreException("ACTOR NOT FOUND");
		}

		filmActor.setActorId(actor.get());
		filmActor.setFilmId(film.get());
		filmActor.setLastUpdate(LocalDateTime.now());
		BeanUtils.copyProperties(filmActorDto, filmActor);
		filmActorRepository.save(filmActor);
		return filmActorDto;
	}

//	@Override
//	public List<FilmDto> assignFilmToActor(Short actorId, FilmDto filmId) {
//		Optional<Actor> findActorById = actorRepository.findById(actorId);
//		Optional<Film> findByFilmId = filmRepository
//		
//		if(!findActorById.isEmpty()&&!findByFilmId.isEmpty()) {
//			Actor actor= findActorById.get();
//			Film film = findByFilmId.get();
//			actor.getFilmActors().get(actor.get);
//			
//		}
//	}	
//		

	@Override
	public List<ActorDto> getActorsByFilmId(Short filmId) {
		List<Actor> findByFilmId = filmActorRepository.findByFilmId(filmId);
		List<ActorDto> dtos = new ArrayList<>();
		for (Actor actor : findByFilmId) {
			ActorDto dto = new ActorDto();
			BeanUtils.copyProperties(actor, dto);
			dtos.add(dto);
		}
		return dtos;

	}

	@Override
	public List<FilmDto> searchFilmsByActorId(Short actorId) {
		List<Film> findByactorId = filmActorRepository.findByActorId(actorId);
		List<FilmDto> dtos = new ArrayList<>();
		for (Film film : findByactorId) {
			FilmDto dto = filmService.covertToFilmDto(film);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<FilmDto> assignFilmToActor(Short actorId, Short filmId) {
//		Optional<Actor> id = actorRepository.findById(actorId);
//		Optional<Film> findById = filmRepository.findById(filmId);
		int assignFilmToActor = filmActorRepository.assignFilmToActor(filmId, actorId);
		System.out.println(assignFilmToActor);

		List<Film> findByActorId = filmActorRepository.findByActorId(actorId);
		if(findByActorId.isEmpty()) {
			throw new FilmRentalStoreException("FILMS NOT FOUND");
		}
		List<FilmDto> dtos = new ArrayList<>();
		if (!findByActorId.isEmpty()) {
			for (Film film : findByActorId) {
				FilmDto dto = new FilmDto();
				BeanUtils.copyProperties(film, dto);
				dtos.add(dto);

			}
			return dtos;
		}
		throw new FilmRentalStoreException(" CAN NOT ASSIGN FILM TO ACTOR");

	}

}
