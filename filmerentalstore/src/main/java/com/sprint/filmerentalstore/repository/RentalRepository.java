package com.sprint.filmerentalstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Short> {

	Rental findRentalByRentalId(Short id);

	@Query("SELECT r.inventoryId.filmId.title FROM Rental r WHERE r.customerId.customerId = :customerId")
	List<String> findFilmsByCustomer(Short customerId);

	@Query("SELECT r.inventoryId.filmId.title FROM Rental r GROUP BY r.inventoryId.filmId.title ORDER BY COUNT(*) DESC LIMIT 10")
	List<String> findTopTenRentedFilms();

	@Query("SELECT r.inventoryId.filmId.title FROM Rental r WHERE r.inventoryId.storeId.storeId = :storeId GROUP BY r.inventoryId.filmId.title ORDER BY COUNT(*) DESC LIMIT 10")
	List<String> findTopTenRentedFilmsByStore(Short storeId);

	@Query("SELECT r.customerId.firstName || ' ' || r.customerId.lastName FROM Rental r WHERE r.inventoryId.storeId.storeId = :storeId AND r.returnDate IS NULL")
	List<String> findCustomersWithPendingReturns(Short storeId);

}
