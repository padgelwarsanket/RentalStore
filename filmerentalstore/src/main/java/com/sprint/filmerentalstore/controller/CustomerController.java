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

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	/**
	 * Updates customer information.
	 * 
	 * @param customerId  The ID of the customer.
	 * @param customerDto The CustomerDto containing the updated details.
	 * @return ResponseEntity containing the updated CustomerDto.
	 */
	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomerInformation(@PathVariable("customerId") Short customerId,
			@Valid @RequestBody CustomerDto customerDto) {

		CustomerDto customerDtos = service.updateCustomerInformation(customerId, customerDto);

		return new ResponseEntity<CustomerDto>(customerDtos, HttpStatus.OK);
	}

	/**
	 * Retrieves all customers.
	 * 
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer() {
		System.out.println("Inside get all customer");
		List<Customer> customerDtos = service.getAllCustomer();
		System.out.println(customerDtos);
		return new ResponseEntity<List<Customer>>(customerDtos, HttpStatus.OK);
	}

	/**
	 * Creates a new customer.
	 * 
	 * @param CustomerDto The CustomerDto to be created.
	 * @return ResponseEntity containing the created CustomerDto.
	 */
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto CustomerDto) {
		CustomerDto createdCustomerDto = service.registerCustomer(CustomerDto);
		return ResponseEntity.ok(createdCustomerDto);
	}

	/**
	 * Retrieves customers by last name.
	 * 
	 * @param ln The last name of the customers.
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<Customer>> getCustomerByLastName(@PathVariable String ln) {
		List<Customer> CustomerDtos = service.getCustomerByLastName(ln);
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Retrieves customers by first name.
	 * 
	 * @param fn The first name of the customers.
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<Customer>> getCustomerByFirstName(@PathVariable String fn) {
		List<Customer> CustomerDtos = service.getCustomerByFirstName(fn);
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Retrieves a customer by email.
	 * 
	 * @param email The email of the customer.
	 * @return ResponseEntity containing the Customer.
	 */
	@GetMapping("/email/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
		Customer CustomerDtos = service.getCustomerByEmail(email);
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Sets the address for a customer.
	 * 
	 * @param id        The ID of the customer.
	 * @param addressId The ID of the address to set.
	 * @return ResponseEntity containing the updated AddressDto.
	 */
	@PutMapping("/{id}/address")
	public ResponseEntity<AddressDto> setCustomerAddressData(@PathVariable Short id, @RequestBody Short addressId) {
		AddressDto address = service.setCustomerAddress(id, addressId);
		return ResponseEntity.ok(address);
	}

	/**
	 * Retrieves customers by city.
	 * 
	 * @param city The city of the customers.
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> getCustomerByCity(@PathVariable String city) {
		List<Customer> customerDtos = service.getCustomerByCity(city);
		System.out.println(customerDtos);
		return ResponseEntity.ok(customerDtos);
	}

	/**
	 * Retrieves customers by country.
	 * 
	 * @param country The country of the customers.
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> getCustomerByCountry(@PathVariable String country) {
		List<Customer> customerDtos = service.getCustomerByCountry(country);
		// System.out.println(customerDtos);
		return ResponseEntity.ok(customerDtos);
	}

	/**
	 * Retrieves active customers.
	 * 
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/active")
	public ResponseEntity<List<Customer>> getActiveCustomer() {
		List<Customer> CustomerDtos = service.getAllActiveCustomer("1");
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Retrieves inactive customers.
	 * 
	 * @return ResponseEntity containing the list of Customer.
	 */
	@GetMapping("/inactive")
	public ResponseEntity<List<Customer>> getInactiveCustomer() {
		List<Customer> CustomerDtos = service.getAllInactiveCustomer("0");
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Retrieves a customer by phone number.
	 * 
	 * @param phone The phone number of the customer.
	 * @return ResponseEntity containing the Customer.
	 */
	@GetMapping("/phone/{phone}")
	public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
		Customer CustomerDtos = service.getCustomerByPhone(phone);
		return ResponseEntity.ok(CustomerDtos);
	}

	/**
	 * Updates the first name of a customer.
	 * 
	 * @param id        The ID of the customer.
	 * @param firstName The updated first name.
	 * @return ResponseEntity containing the updated CustomerDto.
	 * @throws FilmRentalStoreException if the customer is not found.
	 */
	@PutMapping("/update/fn/{id}")
	public ResponseEntity<CustomerDto> updateCustomerFirstName(@PathVariable Short id, @RequestBody String firstName)
			throws FilmRentalStoreException {
		CustomerDto updatedCustomerDto = service.updateCustomerFirstName(id, firstName);
		return ResponseEntity.ok(updatedCustomerDto);
	}

	/**
	 * Updates the last name of a customer.
	 * 
	 * @param id       The ID of the customer.
	 * @param lastName The updated last name.
	 * @return ResponseEntity containing the updated CustomerDto.
	 * @throws FilmRentalStoreException if the customer is not found.
	 */
	@PutMapping("/update/ln/{id}")
	public ResponseEntity<CustomerDto> updateCustomerLastName(@PathVariable Short id, @RequestBody String lastName)
			throws FilmRentalStoreException {
		CustomerDto updatedCustomerDto = service.updateCustomerLastName(id, lastName);
		return ResponseEntity.ok(updatedCustomerDto);
	}

	/**
	 * Updates the email of a customer.
	 * 
	 * @param id    The ID of the customer.
	 * @param email The updated email.
	 * @return ResponseEntity containing the updated CustomerDto.
	 * @throws FilmRentalStoreException if the customer is not found.
	 */
	@PutMapping("/update/email/{id}")
	public ResponseEntity<CustomerDto> updateCustomerEmail(@PathVariable Short id, @RequestBody String email)
			throws FilmRentalStoreException {
		CustomerDto updatedCustomerDto = service.updateCustomerEmail(id, email);
		return ResponseEntity.ok(updatedCustomerDto);
	}

	/**
	 * Updates the store of a customer.
	 * 
	 * @param id      The ID of the customer.
	 * @param storeId The updated store ID.
	 * @return ResponseEntity containing the updated CustomerDto.
	 * @throws FilmRentalStoreException if the customer or store is not found.
	 */
	@PutMapping("/update/store/{id}")
	public ResponseEntity<CustomerDto> updateCustomerStore(@PathVariable Short id, @RequestBody Short storeId)
			throws FilmRentalStoreException {
		CustomerDto updatedCustomerDto = service.updateCustomerStore(id, storeId);

		return ResponseEntity.ok(updatedCustomerDto);
	}

	/**
	 * Updates the phone number of a customer.
	 * 
	 * @param id    The ID of the customer.
	 * @param phone The updated phone number.
	 * @return ResponseEntity containing the updated CustomerDto.
	 * @throws FilmRentalStoreException if the customer is not found.
	 */
	@PutMapping("/update/phone/{id}")
	public ResponseEntity<CustomerDto> updateCustomerPhone(@PathVariable Short id, @RequestBody String phone)
			throws FilmRentalStoreException {
		CustomerDto updatedCustomerDto = service.updateCustomerPhone(id, phone);
		return ResponseEntity.ok(updatedCustomerDto);
	}
}
