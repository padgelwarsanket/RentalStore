package com.sprint.filmerentalstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Actor;
import com.sprint.filmerentalstore.entity.Film;
import com.sprint.filmerentalstore.entity.FilmActor;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Short> {

	@Query("SELECT fa.actorId FROM FilmActor fa WHERE fa.filmId.filmId = :filmId")
	List<Actor> findByFilmId(Short filmId);

	@Query("SELECT fa.filmId FROM FilmActor fa WHERE fa.actorId.id=:actorId")
	List<Film> findByActorId(Short actorId);

//	@Modifying
//	@Query("UPDATE FilmActor f SET f.filmId.filmId = :filmId WHERE f.actorId.id = :id")
//	int assignFilmToActor(@Param("filmId") Short filmId, @Param("id") Short id);
	
	@Modifying
	@Query(value="UPDATE film_actor f SET f.actor_id = :id WHERE f.film_id = :filmId", nativeQuery = true)
	int assignFilmToActor(@Param("filmId") Short filmId, @Param("id") Short id);

}
