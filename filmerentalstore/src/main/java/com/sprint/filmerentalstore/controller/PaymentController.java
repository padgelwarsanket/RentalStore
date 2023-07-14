package com.sprint.filmerentalstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.PaymentDto;
import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.service.PaymentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	/**
	 * Generates a payment based on the provided rental details.
	 * 
	 * @param rental The rental details.
	 * @return ResponseEntity containing the generated PaymentDto.
	 */
	@PostMapping("/add/rental")
	public ResponseEntity<PaymentDto> addRental(@Valid @RequestBody RentalDto rental) {
		System.out.println(rental);
		PaymentDto dto = paymentService.generatePayment(rental.getInventoryId(), rental.getCustomerId(),
				rental.getStaffId());
		return ResponseEntity.ok(dto);
	}

	/**
	 * Retrieves all payments.
	 * 
	 * @return ResponseEntity containing the list of PaymentDto.
	 */
	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayment() {
		List<PaymentDto> paymentDtos = paymentService.getAllPayment();
		return new ResponseEntity<List<PaymentDto>>(paymentDtos, HttpStatus.OK);
	}

	/**
	 * Retrieves a payment by its ID.
	 * 
	 * @param id The ID of the payment.
	 * @return ResponseEntity containing the PaymentDto.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentByIdData(@PathVariable Short id) {
		PaymentDto payment = paymentService.getPaymentById(id);
		return ResponseEntity.ok(payment);
	}

	/**
	 * Adds a new payment.
	 * 
	 * @param payment The PaymentDto to be added.
	 * @return ResponseEntity containing the added PaymentDto.
	 */
	@PostMapping("/add")
	public ResponseEntity<PaymentDto> addPayment(@Valid @RequestBody PaymentDto payment) {
		System.out.println(payment.toString());
		PaymentDto dto = paymentService.addPayment(payment);
		return ResponseEntity.ok(dto);
	}

	/**
	 * Calculates the cumulative revenue date-wise.
	 * 
	 * @return List of Map containing revenue data.
	 */
	@GetMapping("/revenue/datewise")
	public List<Map<String, Object>> getRevenueDatewise() {
		return paymentService.claculateCumalativeRevenueDatewise();
	}

	/**
	 * Retrieves revenue date-wise for a specific store.
	 * 
	 * @param storeId The ID of the store.
	 * @return List of Map containing revenue data.
	 */
	@GetMapping("/revenue/datewise/store/{storeId}")
	public List<Map<String, Object>> getRevenueDatewiseByStoreId(@PathVariable Short storeId) {
		return paymentService.getRevenueByDateAndStore(storeId);
	}

	/**
	 * Retrieves revenue by film ID.
	 * 
	 * @param filmId The ID of the film.
	 * @return List of Map containing revenue data.
	 */
	@GetMapping("/revenue/film/{filmId}")
	public List<Map<String, Object>> getRevenueByFilm(@PathVariable Short filmId) {
		return paymentService.getRevenueByFilmId(filmId);
	}

	/**
	 * Retrieves revenue by store ID.
	 * 
	 * @param storeId The ID of the store.
	 * @return List of Map containing revenue data.
	 */
	@GetMapping("/revenue/films/store/{storeId}")
	public List<Map<String, Object>> getRevenueByStore(@PathVariable Short storeId) {
		return paymentService.getRevenueByStoreId(storeId);
	}

}
