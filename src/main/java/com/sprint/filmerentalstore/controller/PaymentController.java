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
	
	@PostMapping("/add/rental")
	 public ResponseEntity<PaymentDto> addRental(@Valid @RequestBody RentalDto rental) {
	 System.out.println(rental);
	 PaymentDto dto = paymentService.generatePayment(rental.getInventoryId(), rental.getCustomerId(), rental.getStaffId());
	 return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayment() {
		List<PaymentDto> paymentDtos = paymentService.getAllPayment();
		return new ResponseEntity<List<PaymentDto>>(paymentDtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentByIdData(@PathVariable Short id) {
		PaymentDto payment = paymentService.getPaymentById(id);
		return ResponseEntity.ok(payment);
	}

	@PostMapping("/add")
	public ResponseEntity<PaymentDto> addPayment(@Valid @RequestBody PaymentDto payment) {
		System.out.println(payment.toString());
		PaymentDto dto = paymentService.addPayment(payment);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/revenue/datewise")
    public List<Map<String, Object>>getRevenueDatewise() {
        return paymentService.claculateCumalativeRevenueDatewise();
    }
	
	@GetMapping("/revenue/datewise/store/{storeId}")
    public List<Map<String, Object>>getRevenueDatewiseByStoreId(@PathVariable Short storeId) {
         return paymentService.getRevenueByDateAndStore(storeId);
    }
	
	@GetMapping("/revenue/film/{filmId}")
	public List<Map<String, Object>>getRevenueByFilm(@PathVariable Short filmId) {
        return paymentService.getRevenueByFilmId(filmId);
   }
	
	@GetMapping("/revenue/films/store/{storeId}")
	public List<Map<String, Object>>getRevenueByStore(@PathVariable Short storeId) {
        return paymentService.getRevenueByStoreId(storeId);
   }

}




