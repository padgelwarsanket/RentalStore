package com.sprint.filmerentalstore.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.PaymentDto;
import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.service.RentalService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/rental")
public class RentalController {

	@Autowired
	private RentalService rentalService;

	/**
	 * Retrieves all rental data.
	 * 
	 * @return ResponseEntity containing the list of RentalDto.
	 */
	@GetMapping
	public ResponseEntity<List<RentalDto>> getAllRentalData() {
		List<RentalDto> rentalDto = rentalService.getAllRental();
		return new ResponseEntity<List<RentalDto>>(rentalDto, HttpStatus.OK);
	}

	/**
	 * Adds a new rental.
	 * 
	 * @param dto The RentalDto to be added.
	 * @return ResponseEntity containing the added RentalDto.
	 */
	@PostMapping("/add")
	public ResponseEntity<RentalDto> addREntal(@Valid @RequestBody RentalDto dto) {
		System.out.println(dto.toString());
		RentalDto rentalDto = rentalService.addRental(dto);
		return ResponseEntity.ok(rentalDto);
	}

	/**
	 * Retrieves a rental by its ID.
	 * 
	 * @param id The ID of the rental.
	 * @return ResponseEntity containing the RentalDto.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RentalDto> getRentalByIdData(@PathVariable Short id) {
		RentalDto rental = rentalService.getRentalById(id);
		return ResponseEntity.ok(rental);
	}

	/**
	 * Retrieves films rented by a customer.
	 * 
	 * @param customerId The ID of the customer.
	 * @return List of String containing film names.
	 */
	@GetMapping("/customer/{id}")
	public List<String> getFilmsByCustomer(@PathVariable("id") Short customerId) {
		return rentalService.getFilmsByCustomer(customerId);
	}

	/**
	 * Retrieves the top ten rented films.
	 * 
	 * @return List of String containing film names.
	 */
	@GetMapping("/toptenfilms")
	public List<String> getTopTenRentedFilms() {
		return rentalService.getTopTenRentedFilms();
	}

	/**
	 * Retrieves the top ten rented films by store ID.
	 * 
	 * @param storeId The ID of the store.
	 * @return List of String containing film names.
	 */
	@GetMapping("/toptenfilms/store/{id}")
	public List<String> getTopTenRentedFilmsByStore(@PathVariable("id") Short storeId) {
		return rentalService.getTopTenRentedFilmsByStore(storeId);
	}

	/**
	 * Retrieves customers with pending returns by store ID.
	 * 
	 * @param storeId The ID of the store.
	 * @return List of String containing customer names.
	 */
	@GetMapping("/due/store/{id}")
	public List<String> getCustomersWithPendingReturns(@PathVariable("id") Short storeId) {
		return rentalService.getCustomersWithPendingReturns(storeId);
	}

	/**
	 * Updates the return date of a rental.
	 * 
	 * @param rentalId   The ID of the rental.
	 * @param returnDate The new return date.
	 * @return A success message.
	 */
	@PutMapping("/update/returndate/{id}")
	public String updateReturnDate(@PathVariable("id") Short rentalId, @Valid @RequestBody LocalDate returnDate) {
		rentalService.updateReturnDate(rentalId, returnDate);
		return "Rental Return Date Updated Successfully";
	}

}
