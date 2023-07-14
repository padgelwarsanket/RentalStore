package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.ActorDto;
import com.sprint.filmerentalstore.dto.CustomDto;
import com.sprint.filmerentalstore.entity.Actor;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.ActorRepository;
//import com.sprint.filmerentalstore.service.ActorFilmCountDto;
import com.sprint.filmerentalstore.service.ActorService;
//import com.sprint.filmerentalstore.service.FilmDto;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorRepository actorRepository;

	@Override
	public ActorDto getByactorId(Short id) {
		Optional<Actor> findById = actorRepository.findById(id);
		ActorDto actordto = new ActorDto();
		BeanUtils.copyProperties(findById, actordto);
		return actordto;

	}

	@Override
	public ActorDto createActor(ActorDto actorDto) {

		// TODO Auto-generated method stub

		Actor actor = new Actor();
		BeanUtils.copyProperties(actorDto, actor);
		actor.setLastUpdate(LocalDateTime.now());
		actorDto.setLastUpdate(LocalDateTime.now());
		actorRepository.save(actor);
		return actorDto;
	}

	@Override
	public List<ActorDto> searchAll() {
		List<Actor> actors = actorRepository.findAll();
		List<ActorDto> dtos = new ArrayList<>();

		if(actors.isEmpty()) {
			throw  new  FilmRentalStoreException("NOT FOUND");
		}
		for (Actor actor : actors) {
			ActorDto actorDto = new ActorDto();
			BeanUtils.copyProperties(actor, actorDto);
			dtos.add(actorDto);
		}

		return dtos;

	}

	@Override
	public List<ActorDto> searchActorsByLastName(String lastName) {
		// TODO Auto-generated method stub

		List<Actor> actors = actorRepository.findByLastName(lastName);
		List<ActorDto> dtos = new ArrayList<>();
		if(actors.isEmpty()) {
			throw  new  FilmRentalStoreException("NOT FOUND");
		}
		
		for (Actor actor : actors) {
			ActorDto actorDto = new ActorDto();
			BeanUtils.copyProperties(actor, actorDto);
			dtos.add(actorDto);
		}

		return dtos;
	}

	@Override
	public List<ActorDto> searchActorsByFirstName(String firstName) {

		List<Actor> actors = actorRepository.findByFirstName(firstName);
		List<ActorDto> dtos = new ArrayList<>();
		if(actors.isEmpty()) {
			throw  new  FilmRentalStoreException("NOT FOUND");
		}
		
		for (Actor actor : actors) {
			ActorDto actorDto = new ActorDto();
			BeanUtils.copyProperties(actor, actorDto);
			dtos.add(actorDto);
		}

		return dtos;
	}

	@Override
	public ActorDto updateActorLastName(Short id, String lastName) {
		Optional<Actor> optionalActor = actorRepository.findById(id);
		if (!optionalActor.isPresent()) {
			throw new FilmRentalStoreException("the Actor is not found");
		}
		Actor actor = optionalActor.get();
		actor.setLastName(lastName);
		actor.setLastUpdate(LocalDateTime.now());
		actor = actorRepository.save(actor);
		ActorDto actorDto = new ActorDto();
		BeanUtils.copyProperties(actor, actorDto);
		return actorDto;

		// or exception if actor with the given ID is not found
	}

	@Override
	public ActorDto updateActorFirstName(Short id, String firstName) {
		Optional<Actor> actorOptional = actorRepository.findById(id);

		if (actorOptional.isPresent()) {
			Actor actor = actorOptional.get();
			actor.setFirstName(firstName);
			actor.setLastUpdate(LocalDateTime.now());
			actorRepository.save(actor);

			ActorDto actorDto = new ActorDto();
			BeanUtils.copyProperties(actor, actorDto);
			return actorDto;
		}

		throw new FilmRentalStoreException("the Actor is not found");
	}

	@Override
	public List<CustomDto> getTopTenActorsByFilmCount() {
		List<Object[]> results = actorRepository.getCustomDto(PageRequest.of(0, 10));

		if (results.isEmpty()) {
			throw new FilmRentalStoreException("LIST NOT FOUND");
		}
		List<CustomDto> customDtos = new ArrayList<>();
		for (Object[] result : results) {
			Short id = ((Number) result[0]).shortValue();
			String firstName = (String) result[1];
			Integer filmCount = ((Number) result[2]).intValue();
			CustomDto customDto = new CustomDto(id, firstName, filmCount);
			customDtos.add(customDto);
		}

		return customDtos;
	}

}
