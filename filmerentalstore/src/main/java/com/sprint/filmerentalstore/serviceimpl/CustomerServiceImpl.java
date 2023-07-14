package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.AddressRepository;
import com.sprint.filmerentalstore.repository.CityRepository;
import com.sprint.filmerentalstore.repository.CustomerRepository;
import com.sprint.filmerentalstore.repository.StoreRepository;
import com.sprint.filmerentalstore.service.CustomerService;

/**
 * Implementation of the {@link CustomerService} interface.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StoreRepository storeRepository;

	/**
	 * Registers a new customer.
	 *
	 * @param dto the customer data transfer object
	 * @return the registered customer data transfer object
	 */
	public CustomerDto registerCustomer(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setStoreId(storeRepository.getById(dto.getStoreId()));
		customer.setAddressId(addressRepository.getById(dto.getAddressId()));
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getEmail());
		customer.setActive(dto.getActive());
		customer.setCreateDate(LocalDate.now());
		customer.setLastUpdate(LocalDateTime.now());
		dto.setCreateDate(LocalDate.now());
		dto.setLastUpdate(LocalDateTime.now());
		customer.setLastUpdate(dto.getLastUpdate());
		BeanUtils.copyProperties(dto, customer);
		customerRepository.save(customer);
		return dto;
	}

	/**
	 * Retrieves customers by last name.
	 *
	 * @param lastName the last name of the customers to retrieve
	 * @return the list of customers with the given last name
	 * @throws FilmRentalStoreException if no customers with the given last name exist
	 */
	public List<Customer> getCustomerByLastName(String lastName) {
		List<Customer> byLastName = customerRepository.getByLastName(lastName);
		if (!byLastName.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : byLastName) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return byLastName;
		}
		throw new FilmRentalStoreException("Customer with such name does not exist");
	}

	/**
	 * Retrieves customers by first name.
	 *
	 * @param firstName the first name of the customers to retrieve
	 * @return the list of customers with the given first name
	 * @throws FilmRentalStoreException if no customers with the given first name exist
	 */
	public List<Customer> getCustomerByFirstName(String firstName) {
		List<Customer> byFirstName = customerRepository.getByFirstName(firstName);
		if (!byFirstName.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : byFirstName) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return byFirstName;
		}
		throw new FilmRentalStoreException("Customer with given name does not exist");
	}

	/**
	 * Retrieves a customer by email.
	 *
	 * @param email the email of the customer to retrieve
	 * @return the customer with the given email
	 * @throws FilmRentalStoreException if no customer with the given email exists
	 */
	public Customer getCustomerByEmail(String email) {
		Optional<Customer> customer = customerRepository.getByEmail(email);
		if (customer.isPresent()) {
			CustomerDto dto = copyEntityToDto(customer.get());
			return customer.get();
		}
		throw new FilmRentalStoreException("Customer with given email does not exist");
	}

	/**
	 * Sets the address for a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param addressId  the ID of the address to set
	 * @return the updated address data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID or the address with the given ID does not exist
	 */
	public AddressDto setCustomerAddress(Short customerId, Short addressId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			Optional<Address> address = addressRepository.getByAddressId(addressId);
			cust.setAddressId(address.get());
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			AddressDto dto = addressServiceImpl.copyEntityToDto(address.get());
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Retrieves customers by city.
	 *
	 * @param city the city of the customers to retrieve
	 * @return the list of customers from the given city
	 * @throws FilmRentalStoreException if no customers from the given city exist
	 */
	public List<Customer> getCustomerByCity(String city) {
		List<Customer> findByAddressId_cityId = customerRepository.getByAddressId_cityId_city(city);
		if (!findByAddressId_cityId.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : findByAddressId_cityId) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return findByAddressId_cityId;
		}
		throw new FilmRentalStoreException("Customer with given city does not exist");
	}

	/**
	 * Retrieves customers by country.
	 *
	 * @param country the country of the customers to retrieve
	 * @return the list of customers from the given country
	 * @throws FilmRentalStoreException if no customers from the given country exist
	 */
	public List<Customer> getCustomerByCountry(String country) {
		List<Customer> customers = customerRepository.getByAddressId_cityId_countryId_country(country);
		if (!customers.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : customers) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return customers;
		}
		throw new FilmRentalStoreException("Customer with given country does not exist");
	}

	/**
	 * Retrieves all active customers.
	 *
	 * @param str the active status to filter the customers ("active" or "inactive")
	 * @return the list of active customers
	 * @throws FilmRentalStoreException if no active customers exist
	 */
	public List<Customer> getAllActiveCustomer(String str) {
		List<Customer> findByActive = customerRepository.getByActive(str);
		if (!findByActive.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : findByActive) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return findByActive;
		}
		throw new FilmRentalStoreException("No active customer exist");
	}

	/**
	 * Retrieves all inactive customers.
	 *
	 * @param str the active status to filter the customers ("active" or "inactive")
	 * @return the list of inactive customers
	 * @throws FilmRentalStoreException if no inactive customers exist
	 */
	public List<Customer> getAllInactiveCustomer(String str) {
		List<Customer> findByInActive = customerRepository.getByActive(str);
		if (!findByInActive.isEmpty()) {
			List<CustomerDto> dtos = new ArrayList<>();
			for (Customer cust : findByInActive) {
				CustomerDto dto = copyEntityToDto(cust);
				dtos.add(dto);
			}
			return findByInActive;
		}
		throw new FilmRentalStoreException("No inactive customer exist");
	}

	/**
	 * Retrieves a customer by phone.
	 *
	 * @param phone the phone number of the customer to retrieve
	 * @return the customer with the given phone number
	 * @throws FilmRentalStoreException if no customer with the given phone number exists
	 */
	@Override
	public Customer getCustomerByPhone(String phone) {
		Optional<Customer> findByAddressId_Phone = customerRepository.getByAddressId_Phone(phone);
		if (findByAddressId_Phone.isPresent()) {
			CustomerDto dto = copyEntityToDto(findByAddressId_Phone.get());
			return findByAddressId_Phone.get();
		}
		throw new FilmRentalStoreException("Customer with given contact does not exist");
	}

	/**
	 * Updates the first name of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param firstName  the new first name
	 * @return the updated customer data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID does not exist
	 */
	public CustomerDto updateCustomerFirstName(Short customerId, String firstName) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			cust.setFirstName(firstName);
			cust.setCreateDate(LocalDate.now());
			cust.setLastUpdate(LocalDateTime.now());
			customerRepository.save(cust);
			CustomerDto dto = copyEntityToDto(cust);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Updates the last name of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param lastName   the new last name
	 * @return the updated customer data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID does not exist
	 */
	public CustomerDto updateCustomerLastName(Short customerId, String lastName) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			cust.setLastName(lastName);
			cust.setCreateDate(LocalDate.now());
			cust.setLastUpdate(LocalDateTime.now());
			customerRepository.save(cust);
			CustomerDto dto = copyEntityToDto(cust);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Updates the email of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param email      the new email
	 * @return the updated customer data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID does not exist
	 */
	public CustomerDto updateCustomerEmail(Short customerId, String email) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			cust.setEmail(email);
			cust.setCreateDate(LocalDate.now());
			cust.setLastUpdate(LocalDateTime.now());
			customerRepository.save(cust);
			CustomerDto dto = copyEntityToDto(cust);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Updates the store of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param storeId    the ID of the store to set
	 * @return the updated customer data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID or the store with the given ID does not exist
	 */
	public CustomerDto updateCustomerStore(Short customerId, Short storeId) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			Optional<Store> store = storeRepository.findById(storeId);
			cust.setStoreId(store.get());
			cust.setCreateDate(LocalDate.now());
			cust.setLastUpdate(LocalDateTime.now());
			customerRepository.save(cust);
			CustomerDto dto = copyEntityToDto(cust);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Updates the phone number of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param phone      the new phone number
	 * @return the updated customer data transfer object
	 * @throws FilmRentalStoreException if the customer with the given ID does not exist
	 */
	public CustomerDto updateCustomerPhone(Short customerId, String phone) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer entity = customer.get();
			Optional<Address> findByAddressId = addressRepository.getByAddressId(entity.getAddressId().getAddressId());
			findByAddressId.get().setPhone(phone);
			entity.setAddressId(findByAddressId.get());
			System.out.println(entity);
			entity.setCreateDate(LocalDate.now());
			entity.setLastUpdate(LocalDateTime.now());
			customerRepository.save(entity);
			CustomerDto dto = copyEntityToDto(entity);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

	/**
	 * Retrieves all customers.
	 *
	 * @return the list of all customers
	 */
	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list = customerRepository.findAll();
		System.out.println(list);
		return list;
	}

	/**
	 * Updates the information of a customer.
	 *
	 * @param customerId the ID of the customer
	 * @param dto        the updated customer data transfer object
	 * @return the updated customer data transfer object
	 */
	@Override
	public CustomerDto updateCustomerInformation(Short customerId, CustomerDto dto) {
		Optional<Customer> customer1 = customerRepository.findById(customerId);
		customer1.get().setCreateDate(LocalDate.now());
		customer1.get().setLastUpdate(LocalDateTime.now());
		customer1.get().getStoreId().setStoreId(dto.getStoreId());
		customer1.get().setFirstName(dto.getFirstName());
		customer1.get().setLastName(dto.getLastName());
		customer1.get().setEmail(dto.getEmail());
		Customer cust = customerRepository.save(customer1.get());
		return copyEntityToDto(cust);
	}

	/**
	 * Copies the fields from a customer entity to a customer data transfer object.
	 *
	 * @param cust the customer entity
	 * @return the customer data transfer object
	 */
	public CustomerDto copyEntityToDto(Customer cust) {
		CustomerDto dto = new CustomerDto();
		dto.setCustomerId(cust.getCustomerId());
		dto.setAddressId(cust.getAddressId().getAddressId());
		dto.setActive(cust.getActive());
		dto.setCreateDate(cust.getCreateDate());
		dto.setEmail(cust.getEmail());
		dto.setFirstName(cust.getFirstName());
		dto.setLastName(cust.getLastName());
		dto.setLastUpdate(cust.getLastUpdate());
		dto.setStoreId(cust.getStoreId().getStoreId());
		return dto;
	}

}
