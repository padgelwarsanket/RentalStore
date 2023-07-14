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

////	@Autowired
////	private AddressRepository addressRepository;

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

	public Customer getCustomerByEmail(String email) {
		Optional<Customer> customer = customerRepository.getByEmail(email);
		if (customer.isPresent()) {
			CustomerDto dto = copyEntityToDto(customer.get());
			return customer.get();
		}
		throw new FilmRentalStoreException("Customer with given email does not exist");
	}

	public AddressDto setCustomerAddress(Short customerId, Short addressId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			Optional<Address> address = addressRepository.getByAddressId(addressId);
			cust.setAddressId(address.get());
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			AddressDto dto = addressServiceImpl.copyEntityToDto(address.get());
			// BeanUtils.copyProperties(findByAddressId.get(), dto);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");

	}

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

	@Override
	public Customer getCustomerByPhone(String phone) {
		Optional<Customer> findByAddressId_Phone = customerRepository.getByAddressId_Phone(phone);
		if (findByAddressId_Phone.isPresent()) {
			CustomerDto dto = copyEntityToDto(findByAddressId_Phone.get());
			return findByAddressId_Phone.get();
		}
		throw new FilmRentalStoreException("Customer with given contact does not exist");
	}

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

	public CustomerDto updateCustomerStore(Short customerId, Short storeId) throws FilmRentalStoreException {
		Optional<Customer> customer = customerRepository.getByCustomerId(customerId);
		if (customer.isPresent()) {
			Customer cust = customer.get();
			Optional<Store> store = storeRepository.findById(storeId);
//			Store st = store.get();
			cust.setStoreId(store.get());
			cust.setCreateDate(LocalDate.now());
			cust.setLastUpdate(LocalDateTime.now());
			customerRepository.save(cust);
			CustomerDto dto = copyEntityToDto(cust);
			return dto;
		}
		throw new FilmRentalStoreException("Customer with given id does not exist");
	}

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

//	@Override
//	public List<Customer> getAllCustomer() {
//		List<Customer> customers = customerRepository.findAll();
//		List<CustomerDto> dtos = new ArrayList();
//		for (Customer cust : customers) {
//			CustomerDto dto = new CustomerDto();
//			dto = copyEntityToDto(cust);
//			dtos.add(dto);
//		}
//		return customers;
//	}

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

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list = customerRepository.findAll();
		System.out.println(list);
		return list;
	}

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

		//dto.setCustomerId(customerId);

		return copyEntityToDto(cust);

	}

}
