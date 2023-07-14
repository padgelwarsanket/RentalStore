package com.sprint.filmerentalstore.controller;

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

import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.dto.StoreDto;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.service.StoreService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreService service;

//	localhost:9090/api/store
	@GetMapping
	public ResponseEntity<List<StoreDto>> getAllStore() {
		List<StoreDto> storeDto = service.getAllStore();
		return new ResponseEntity<List<StoreDto>>(storeDto, HttpStatus.OK);
	}

//	localhost:9090/api/store
	@PostMapping
	public ResponseEntity<StoreDto> addstaff(@Valid @RequestBody StoreDto storeDto) {
		StoreDto dto = service.registerCustomer(storeDto);
		return new ResponseEntity<StoreDto>(dto, HttpStatus.CREATED);
	}

//	localhost:9090/api/store/3/address
	@PutMapping("/{storeId}/address")
	public ResponseEntity<StoreDto> updateStorByAddress(@PathVariable Short storeId,@Valid @RequestBody Short addressId) {
		StoreDto store = service.updateStoreByAddress(storeId, addressId);
		return new ResponseEntity<>(store, HttpStatus.OK);
	}

//	localhost:9090/api/store/city/Lethbridge
	@GetMapping("/city/{city}")
	public ResponseEntity<List<StoreDto>> getStoreByCity(@PathVariable String city) {
		List<StoreDto> stores = service.getStoreByCity(city);
		return new ResponseEntity<>(stores, HttpStatus.OK);
	}

//	localhost:9090/api/store/country/       	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<StoreDto>> getStoreByCountry(@PathVariable String country) {
		List<StoreDto> storeList = service.getStoreByCountry(country);
		return new ResponseEntity<>(storeList, HttpStatus.OK);
	}

//    localhost:9090/api/store/phone/14033335568
	@GetMapping("/phone/{phone}")
	public ResponseEntity<StoreDto> getStoreByPhone(@PathVariable String phone) {
		StoreDto dto = service.getStoreByPhone(phone);
		return ResponseEntity.ok(dto);
	}

//    localhost:9090/api/store/update/phone/4	
	@PutMapping("/update/phone/{storeId}")
	public ResponseEntity<StoreDto> updatestorePhoneNumber(@PathVariable Short storeId,@Valid @RequestBody Store store) {
		StoreDto dto = service.updateStorePhoneNumber(storeId, store.getAddressId().getPhone());
		return ResponseEntity.ok(dto);
	}

//  localhost:9090/api/store/4/manager	
	@PutMapping("/{storeId}/manager")
	public ResponseEntity<StoreDto> updatestoreManager(@PathVariable Short storeId, @Valid @RequestBody Store store) {
		StoreDto dto = service.updateStorManager(storeId, store.getManagerStaffId());
		return ResponseEntity.ok(dto);
	}

//  localhost:9090/api/store/staff/4
	@GetMapping("staff/{storeId}")
	public ResponseEntity<List<StaffDto>> getStaffOfStore(@PathVariable Short storeId) {
		List<StaffDto> staffList = service.getStaffOfStore(storeId);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

//  localhost:9090/api/store/customers/3
	@GetMapping("customers/{storeId}")
	public ResponseEntity<List<CustomerDto>> getCustomersOfStore(@PathVariable Short storeId) {
		List<CustomerDto> customerList = service.getCustomersOfStore(storeId);
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

//  http://localhost:9090/api/store/manager/1
	@GetMapping("manager/{id}")
	public ResponseEntity<StaffDto> getManagerOfStore(@PathVariable Short id) {
		StaffDto manager = service.getManagerOfStore(id);
		return new ResponseEntity<>(manager, HttpStatus.OK);
	}

	@GetMapping("managers")
	public ResponseEntity<Object> getManagerOfStore() {
		Object manager = service.getManagersOfStore();
		return new ResponseEntity<>(manager, HttpStatus.OK);
	}

}
