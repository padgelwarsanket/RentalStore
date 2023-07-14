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

import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.service.StaffService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService service;

	/**
	 * Updates the phone number of a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The StaffDto containing the updated details.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffDto> updatestorePhoneNumber(@PathVariable Short staffId, @RequestBody StaffDto staff) {
		System.out.println(staff.toString());
		StaffDto dto = service.updateStaff(staffId, staff);
		return ResponseEntity.ok(dto);
	}

	/**
	 * Retrieves all staff members.
	 * 
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping
	public ResponseEntity<List<StaffDto>> getAllStaff() {
		List<StaffDto> staffDto = service.getAllStaff();
		return new ResponseEntity<List<StaffDto>>(staffDto, HttpStatus.OK);
	}

	/**
	 * Adds a new staff member.
	 * 
	 * @param staffDto The StaffDto to be added.
	 * @return ResponseEntity containing the added StaffDto.
	 */
	@PostMapping
	public ResponseEntity<StaffDto> addStaff(@Valid @RequestBody StaffDto staffDto) {
		StaffDto dto = service.addStaff(staffDto);
		return new ResponseEntity<StaffDto>(dto, HttpStatus.CREATED);
	}

	/**
	 * Retrieves staff members by last name.
	 * 
	 * @param lastName The last name of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<StaffDto>> getStaffByLastName(@PathVariable String lastName) {
		List<StaffDto> staffList = service.getStaffByLastName(lastName);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	/**
	 * Retrieves staff members by first name.
	 * 
	 * @param firstName The first name of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/fn/{firstName}")
	public ResponseEntity<List<StaffDto>> getStaffByFirstName(@PathVariable String firstName) {
		List<StaffDto> staffList = service.getStaffByFirstName(firstName);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	/**
	 * Retrieves staff members by email.
	 * 
	 * @param email The email of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/email/{email}")
	public ResponseEntity<List<StaffDto>> getStaffByEmail(@PathVariable String email) {
		List<StaffDto> staffList = service.getStaffByEmail(email);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	/**
	 * Assigns an address to a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the address ID.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("/{staffId}/address")
	public ResponseEntity<StaffDto> assignAddressToStaff(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto staffAddress = service.updateStaffAddress(staffId, staff.getAddressId().getAddressId());
		return ResponseEntity.ok(staffAddress);
	}

	/**
	 * Retrieves staff members by city.
	 * 
	 * @param city The city of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/city/{city}")
	public ResponseEntity<List<StaffDto>> getStaffByCity(@PathVariable String city) {
		List<StaffDto> staffList = service.getStaffByCity(city);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	/**
	 * Retrieves staff members by country.
	 * 
	 * @param country The country of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/country/{country}")
	public ResponseEntity<List<StaffDto>> getStaffByCountry(@PathVariable String country) {
		List<StaffDto> staffList = service.getStaffByCountry(country);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}

	/**
	 * Retrieves staff members by phone number.
	 * 
	 * @param phone The phone number of the staff members.
	 * @return ResponseEntity containing the list of StaffDto.
	 */
	@GetMapping("/phone/{phone}")
	public ResponseEntity<List<StaffDto>> getStaffByPhone(@PathVariable String phone) {
		List<StaffDto> dtos = service.getStaffByPhone(phone);
		return ResponseEntity.ok(dtos);
	}

	/**
	 * Updates the first name of a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the updated first name.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("/update/firstname/{staffId}")
	public ResponseEntity<StaffDto> updateStaffFirstName(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto dto = service.updateStaffFirstName(staffId, staff.getFirstName());
		return ResponseEntity.ok(dto);
	}

	/**
	 * Updates the last name of a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the updated last name.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("update/lastname/{staffId}")
	public ResponseEntity<StaffDto> updateStaffLastName(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto dto = service.updateStaffLastName(staffId, staff.getLastName());
		return ResponseEntity.ok(dto);
	}

	/**
	 * Updates the email of a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the updated email.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("update/email/{staffId}")
	public ResponseEntity<StaffDto> updateStaffEmail(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto dto = service.updateStaffEmail(staffId, staff.getEmail());
		return ResponseEntity.ok(dto);
	}

	/**
	 * Assigns a store to a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the store ID.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("update/store/{staffId}")
	public ResponseEntity<StaffDto> assignStoreToStaff(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto dto = service.updateStoreToStaff(staffId, staff.getStoreId().getStoreId());
		return ResponseEntity.ok(dto);
	}

	/**
	 * Updates the phone number of a staff member.
	 * 
	 * @param staffId The ID of the staff member.
	 * @param staff   The Staff object containing the updated phone number.
	 * @return ResponseEntity containing the updated StaffDto.
	 */
	@PutMapping("update/phone/{staffId}")
	public ResponseEntity<StaffDto> updateStaffPhoneNumber(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
		StaffDto dto = service.updateStaffPhoneNumber(staffId, staff.getAddressId().getPhone());
		return ResponseEntity.ok(dto);
	}
}
