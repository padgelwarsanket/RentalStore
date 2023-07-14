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



@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PutMapping("/update/{customerId}")

    public ResponseEntity<CustomerDto> updateCustomerInformation(@PathVariable("customerId") Short customerId, @Valid @RequestBody CustomerDto customerDto){

        CustomerDto customerDtos = service.updateCustomerInformation(customerId,customerDto);

        return new ResponseEntity<CustomerDto>(customerDtos,HttpStatus.OK);

    }
	
	//localhost:9090/api/customer
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer(){
		System.out.println("Inside get all customer");
		List<Customer> customerDtos = service.getAllCustomer();
		System.out.println(customerDtos);
		return new ResponseEntity<List<Customer>>(customerDtos,HttpStatus.OK);
	}

	
	//localhost:9090/api/customer
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto CustomerDto) {
        CustomerDto createdCustomerDto = service.registerCustomer(CustomerDto);
        return ResponseEntity.ok(createdCustomerDto);
    }

    //localhost:9090/api/customer/lastname/JONES
    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Customer>> getCustomerByLastName(@PathVariable String ln) {
        List<Customer> CustomerDtos =  service.getCustomerByLastName(ln);
        return ResponseEntity.ok(CustomerDtos);
    }

   // localhost:9090/api/customer/firstname/DONALD
    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Customer>> getCustomerByFirstName(@PathVariable String fn) {
        List<Customer> CustomerDtos = service.getCustomerByFirstName(fn);
        return ResponseEntity.ok(CustomerDtos);
    }
//localhost:9090/api/customer/email/BARBARA.JONES@sakilacustomer.org
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer CustomerDtos = service.getCustomerByEmail(email);
        return ResponseEntity.ok(CustomerDtos);
    }

    //localhost:9090/api/customer/1/address
    @PutMapping("/{id}/address")
    public ResponseEntity<AddressDto> setCustomerAddressData(@PathVariable Short id,@RequestBody Short addressId) {
        AddressDto address = service.setCustomerAddress(id, addressId);
        return ResponseEntity.ok(address);
    }

    //localhost:9090/api/customer/city/Abha
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customer>> getCustomerByCity(@PathVariable String city) {
        List<Customer> customerDtos = service.getCustomerByCity(city);
        System.out.println(customerDtos);
        return ResponseEntity.ok(customerDtos);
    }

    //localhost:9090/api/customer/country/JAPAN
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Customer>> getCustomerByCountry(@PathVariable String country) {
        List<Customer> customerDtos = service.getCustomerByCountry(country);
        //System.out.println(customerDtos);
        return ResponseEntity.ok(customerDtos);
    }

    //localhost:9090/api/customer/active
    @GetMapping("/active")
    public ResponseEntity<List<Customer>> getActiveCustomer() {
        List<Customer> CustomerDtos = service.getAllActiveCustomer("1");
        return ResponseEntity.ok(CustomerDtos);
    }

    //localhost:9090/api/customer/inactive
    @GetMapping("/inactive")
    public ResponseEntity<List<Customer>> getInactiveCustomer() {
        List<Customer> CustomerDtos = service.getAllInactiveCustomer("0");
        return ResponseEntity.ok(CustomerDtos);
    }

    
   //localhost:9090/api/customer/phone/949312333307
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
        Customer CustomerDtos = service.getCustomerByPhone(phone);
        return ResponseEntity.ok(CustomerDtos);
    }

    //localhost:9090/api/customer/update/fn/1
    @PutMapping("/update/fn/{id}")
    public ResponseEntity<CustomerDto> updateCustomerFirstName(@PathVariable Short id, @RequestBody String firstName) throws FilmRentalStoreException {
        CustomerDto updatedCustomerDto = service.updateCustomerFirstName(id, firstName);
        return ResponseEntity.ok(updatedCustomerDto);
    }

    
//    localhost:9090/api/customer/update/ln/1
    @PutMapping("/update/ln/{id}")
    public ResponseEntity<CustomerDto> updateCustomerLastName(@PathVariable Short id, @RequestBody String lastName) throws FilmRentalStoreException {
        CustomerDto updatedCustomerDto = service.updateCustomerLastName(id, lastName);
        return ResponseEntity.ok(updatedCustomerDto);
    }

    //localhost:9090/api/customer/update/email/1
    @PutMapping("/update/email/{id}")
    public ResponseEntity<CustomerDto> updateCustomerEmail(@PathVariable Short id, @RequestBody String email) throws FilmRentalStoreException {
        CustomerDto updatedCustomerDto = service.updateCustomerEmail(id, email);
        return ResponseEntity.ok(updatedCustomerDto);
    }

    
//    localhost:9090/api/customer/update/store/1
    @PutMapping("/update/store/{id}")
    public ResponseEntity<CustomerDto> updateCustomerStore(@PathVariable Short id, @RequestBody Short storeId) throws FilmRentalStoreException {
        CustomerDto updatedCustomerDto = service.updateCustomerStore(id, storeId);

        return ResponseEntity.ok(updatedCustomerDto);
    }
//    localhost:9090/api/customer/update/phone/1
    @PutMapping("/update/phone/{id}")
    public ResponseEntity<CustomerDto> updateCustomerPhone(@PathVariable Short id, @RequestBody String phone) throws FilmRentalStoreException {
        CustomerDto updatedCustomerDto = service.updateCustomerPhone(id, phone);
        return ResponseEntity.ok(updatedCustomerDto);


    }
}

