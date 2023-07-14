package com.sprint.filmerentalstore.service;

import java.util.List;

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;

public interface CustomerService {

//	List<CustomerDto> getAllCustomer();
	public List<Customer> getAllCustomer();

	public CustomerDto registerCustomer(CustomerDto dto);

	public List<Customer> getCustomerByLastName(String ln);

	public List<Customer> getCustomerByFirstName(String fn);

	public Customer getCustomerByEmail(String email);

	public AddressDto setCustomerAddress(Short id, Short adId);
	
	public List<Customer> getCustomerByCity(String city);

	public List<Customer> getCustomerByCountry(String country);

	public List<Customer> getAllActiveCustomer(String str);
	
	public List<Customer> getAllInactiveCustomer(String str);
	
	public Customer getCustomerByPhone(String phone);

	public CustomerDto updateCustomerFirstName(Short id, String firstName) throws FilmRentalStoreException;
	
	public CustomerDto updateCustomerLastName(Short id, String lastName) throws FilmRentalStoreException;

	public CustomerDto updateCustomerEmail(Short id, String email) throws FilmRentalStoreException;

	public CustomerDto updateCustomerStore(Short id, Short storeId) throws FilmRentalStoreException;

	public CustomerDto updateCustomerPhone(Short id, String phone) throws FilmRentalStoreException;

	public CustomerDto updateCustomerInformation(Short customerId, CustomerDto customerDto);


	
//

//
//	public CustomerDto updateCustomerStore(Long id, Long storeId) throws NotFoundException;

}
