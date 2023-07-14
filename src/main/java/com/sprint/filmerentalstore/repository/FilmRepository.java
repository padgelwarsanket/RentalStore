package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {
	
	
    Optional<Film> findById(Short filmId);
	
	List<Film> findByTitle(String title);

	List<Film> findByReleaseYear(String releaseYear);

	@Query("SELECT f FROM Film f WHERE f.rentalDuration > :rentalDuration")
	List<Film> findByRentalDurationGreaterThan(Integer rentalDuration);

	@Query("SELECT f FROM Film f WHERE f.rentalDuration < :rentalDuration")
	List<Film> findByRentalDurationSmallerThan(Integer rentalDuration);

	@Query("SELECT f FROM Film f WHERE f.rentalRate > :rentalRate")
	List<Film> findByRentalRateGreaterThan(Double rentalRate);

	@Query("SELECT f FROM Film f WHERE f.rentalRate < :rentalRate")
	List<Film> findByRentalRateSmallerThan(Double rentalRate);

	@Query("SELECT f FROM Film f WHERE f.length > :length")
	List<Film> findByLengthGreaterThan(Integer length);

	@Query("SELECT f FROM Film f WHERE f.length < :length")
	List<Film> findByLengthSmallerThan(Integer length);

	@Query("SELECT f FROM Film f WHERE f.releaseYear BETWEEN :from AND :to")
	List<Film> findByReleaseYearBetween(String from, String to);

	@Query("SELECT f FROM Film f WHERE f.rating > :rating")
	List<Film> findByRatingSmallerThan(String rating);

	@Query("SELECT f FROM Film f WHERE f.rating < :rating")
	List<Film> findByRatingGreaterThan(String rating);

	@Query("SELECT f FROM Film f WHERE f.language.languageId = :languageId")
	List<Film> findByLanguageId(Byte languageId);
	
	@Query("SELECT f.releaseYear, COUNT(f) FROM Film f GROUP BY f.releaseYear")
	List<Object[]> findFilmCountByYear();
//
//	@Query("SELECT fa.actor FROM FilmActor fa WHERE fa.id.filmId = :filmId")
//    List<Actor> findActorsByFilmId(Short filmId);//prajwal
	
	
	//update Qureies
	@Query( "UPDATE Film f SET f.title =:title WHERE f.filmId= :filmId")
	void updateTitleById(Long filmId,String title);
	
	
	
	

}
