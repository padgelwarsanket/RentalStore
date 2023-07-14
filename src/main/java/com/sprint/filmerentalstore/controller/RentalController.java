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
	
//	localhost:9090/api/rental
	@GetMapping
	public ResponseEntity<List<RentalDto>> getAllRentalData(){
		List<RentalDto> rentalDto = rentalService.getAllRental();
		return new ResponseEntity<List<RentalDto>>(rentalDto,HttpStatus.OK); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<RentalDto> addREntal(@Valid @RequestBody RentalDto dto) {
		System.out.println(dto.toString());
		RentalDto rentalDto = rentalService.addRental(dto);
		return ResponseEntity.ok(rentalDto);
	}
	
//	localhost:9090/api/rental/5
	@GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalByIdData(@PathVariable Short id) {
      RentalDto rental = rentalService.getRentalById(id);
      return ResponseEntity.ok(rental);
  }
	
//	http://localhost:9090/api/rental/due/store/1
	@GetMapping("/customer/{id}")
    public List<String> getFilmsByCustomer(@PathVariable("id") Short customerId) {
        return rentalService.getFilmsByCustomer(customerId);
    }

//	http://localhost:9090/api/rental/toptenfilms
    @GetMapping("/toptenfilms")
    public List<String> getTopTenRentedFilms() {
        return rentalService.getTopTenRentedFilms();
    }

//	http://localhost:9090/api/rental/toptenfilms/store/1
    @GetMapping("/toptenfilms/store/{id}")
    public List<String> getTopTenRentedFilmsByStore(@PathVariable("id") Short storeId) {
        return rentalService.getTopTenRentedFilmsByStore(storeId);
    }

//    http://localhost:9090/api/rental/due/store/1
    @GetMapping("/due/store/{id}")
    public List<String> getCustomersWithPendingReturns(@PathVariable("id") Short storeId) {
        return rentalService.getCustomersWithPendingReturns(storeId);
    }

//    http://localhost:9090/api/rental/update/returndate/1
    @PutMapping("/update/returndate/{id}")
    public String updateReturnDate(@PathVariable("id") Short rentalId, @Valid @RequestBody LocalDate returnDate) {
        rentalService.updateReturnDate(rentalId, returnDate);
        return "Rental Return Date Updated Successfully";
    }

}
