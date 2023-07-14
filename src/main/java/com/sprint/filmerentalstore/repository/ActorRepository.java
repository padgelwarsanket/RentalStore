package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Short> {

	// query write for fetch assign film to actor

	Optional<Actor> findById(Short actorId);
	// query write for fetch assign film to actor

	List<Actor> findByLastName(String lastName);

	List<Actor> findByFirstName(String firstName);

	@Query("UPDATE Actor a SET a.lastName = :lastName WHERE a.id = :id")
	void updateLastName(@Param("id") Short id, @Param("lastName") String lastName);

	@Query("UPDATE Actor a SET a.firstName = :firstName WHERE a.id = :id")
	void updateFirstName(@Param("id") Short id, @Param("firstName") String firstName);

	// List<Object[]> getCustomDto(PageRequest of);

	@Query("SELECT a.id AS id, a.firstName AS firstName, COUNT(*) AS filmCount " + "FROM Actor a "
			+ "JOIN a.filmActors fa " + "JOIN fa.filmId f " + "GROUP BY a.id, a.firstName " + "ORDER BY filmCount DESC")
	List<Object[]> getCustomDto(PageRequest pageRequest);

}
