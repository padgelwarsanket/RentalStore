package com.sprint.filmerentalstore.service;

import java.time.LocalDate;
import java.util.List;

import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.entity.Rental;

public interface RentalService {
	
	public List<RentalDto> getAllRental();

	public RentalDto getRentalById(Short id);
	
	public void updateReturnDate(Short rentalId, LocalDate returnDate);
	
	public List<String> getCustomersWithPendingReturns(Short storeId);
	
	public List<String> getTopTenRentedFilmsByStore(Short storeId);
	
	public List<String> getTopTenRentedFilms();
	
	public List<String> getFilmsByCustomer(Short customerId);
	
	public RentalDto addRental(RentalDto dto);


}
