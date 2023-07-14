package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Short> {

	
	Optional<Payment> findByPaymentId(Short id);

//	 @Query("SELECT DATE(p.paymentDate) AS date, SUM(p.amount) AS revenue FROM Payment p GROUP BY DATE(p.paymentDate) ORDER BY DATE(p.paymentDate)")
	@Query(value = "select payment_date as paymentDate,  amount, "
			+ "  sum(amount) over (order by payment_date) as sum " + "from ( " + "  select "
			+ "    cast(payment_date as date) as payment_date, " + "    sum(amount) as amount " + "  from payment "
			+ "  group by cast(payment_date as date) " + ") as p " + "order by payment_date", nativeQuery = true)
	//public List<CumulativeRevenueNative> calculateCumulativeRevenueDatewise();
	 List<Map<String, Object>> calculateCumulativeRevenueDatewise();

//	  SELECT p.payment_id, p.payment_amount, s.store_name
//	  FROM Payment p
//	  JOIN Rental r ON p.rental_id = r.rental_id
//	  JOIN Inventory i ON r.inventory_id = i.inventory_id
//	  JOIN Store s ON i.store_id = s.store_id;

	@Query("SELECT DATE(p.paymentDate) AS Date, " + "SUM(p.amount) AS Revenue, " + "s.storeId AS Store FROM Payment p "
			+ "LEFT JOIN Rental r ON p.rentalId.rentalId = r.rentalId "
			+ "LEFT JOIN Inventory i ON r.inventoryId.inventoryId = i.inventoryId "
			+ "JOIN Store s ON i.storeId.storeId = s.storeId " + "WHERE s.storeId = :storeId "
			+ "GROUP BY DATE(p.paymentDate), s.storeId " + "ORDER BY DATE(p.paymentDate)")
	List<Map<String, Object>> calculateCumulativeRevenueDatewiseByStore(@Param("storeId") Short storeId);

	@Query("SELECT f.title AS Film, " + "SUM(p.amount) AS Revenue " + "FROM Payment p "
			+ "LEFT JOIN Rental r ON p.rentalId.rentalId = r.rentalId "
			+ "LEFT JOIN Inventory i ON r.inventoryId.inventoryId = i.inventoryId "
			+ "JOIN Film f ON i.filmId.filmId = f.filmId " + "GROUP BY p.amount, f.title " + "ORDER BY f.title")
	List<Map<String, Object>> calculateCumulativeRevenueFilmwise();

	@Query("SELECT f.title AS Film_Title, " + "SUM(p.amount) AS Revenue " + "FROM Payment p "
			+ "LEFT JOIN Rental r ON p.rentalId.rentalId = r.rentalId "
			+ "LEFT JOIN Inventory i ON r.inventoryId.inventoryId = i.inventoryId "
			+ "LEFT JOIN Store s ON i.storeId.storeId = s.storeId " + "JOIN Film f ON i.filmId.filmId = f.filmId "
			+ "WHERE s.storeId = :storeId " + "GROUP BY f.title, p.amount " + "ORDER BY p.amount")
	List<Map<String, Object>> calculateCumulativeRevenueByStoreId(@Param("storeId") Short storeId);

	@Query("SELECT a.addressId AS Store_Id, " + "SUM(p.amount) AS Revenue " + "FROM Payment p "
			+ "LEFT JOIN Rental r ON p.rentalId.rentalId = r.rentalId "
			+ "LEFT JOIN Inventory i ON r.inventoryId.inventoryId = i.inventoryId "
			+ "LEFT JOIN Store s ON i.storeId.storeId = s.storeId " + "LEFT JOIN Film f ON i.filmId.filmId = f.filmId "
			+ "JOIN Address a ON s.addressId.addressId = a.addressId " + "WHERE f.filmId = :filmId "
			+ "GROUP BY a.addressId, p.amount " + "ORDER BY p.amount")
	List<Map<String, Object>> calculateCumulativeRevenueByFilmId(@Param("filmId") Short filmId);

}
