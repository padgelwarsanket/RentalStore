package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.dto.StoreDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.repository.AddressRepository;
import com.sprint.filmerentalstore.repository.CityRepository;
import com.sprint.filmerentalstore.repository.CustomerRepository;
import com.sprint.filmerentalstore.repository.StaffRepository;
import com.sprint.filmerentalstore.repository.StoreRepository;
import com.sprint.filmerentalstore.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<StoreDto> getAllStore() {
		List<Store> storeList = storeRepository.findAll();
		System.out.println(storeList);
		List<StoreDto> dtos = new ArrayList<>();
		for (Store store : storeList) {
			StoreDto dto = copyEntityToDto(store);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public StoreDto registerCustomer(StoreDto dto) {
		Store store = new Store();
		store.setAddressId(addressRepository.findById(dto.getAddressId()).get());
		store.setLastUpdate(LocalDateTime.now());
		store.setManagerStaffId(dto.getManagerStaffId());
		store.setManagerStaffId(dto.getManagerStaffId());
		storeRepository.save(store);
		return dto;
	}

	@Override
	public StoreDto updateStoreByAddress(Short storeId, Short addressId) {
		Optional<Store> byStoreId = storeRepository.getByStoreId(storeId);
		Optional<Address> id = addressRepository.getByAddressId(addressId);
		if (byStoreId.isPresent()) {
			byStoreId.get().setAddressId(id.get());
			byStoreId.get().setLastUpdate(LocalDateTime.now());
			storeRepository.save(byStoreId.get());
			StoreDto dto = copyEntityToDto(byStoreId.get());
			return dto;
		}
		return null;
	}

	@Override
	public List<StoreDto> getStoreByCity(String city) {
		List<Store> stores = storeRepository.getByAddressId_cityId_city(city);
		List<StoreDto> dtos = new ArrayList<>();
		for (Store store : stores) {
			StoreDto dto = copyEntityToDto(store);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<StoreDto> getStoreByCountry(String country) {
		List<Store> byCountry = storeRepository.getByAddressId_cityId_countryId_country(country);
		List<StoreDto> dtos = new ArrayList<>();
		for (Store store : byCountry) {
			StoreDto dto = copyEntityToDto(store);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public StoreDto getStoreByPhone(String phone) {
		Optional<Store> store = storeRepository.getByAddressId_phone(phone);
		if (store.isPresent()) {
			StoreDto dto = copyEntityToDto(store.get());
			return dto;
		}
		return null;
	}

	@Override
	public StoreDto updateStorePhoneNumber(Short storeId, String phone) {
		Optional<Store> store = storeRepository.getByStoreId(storeId);
		if (store.isPresent()) {
			store.get().getAddressId().setPhone(phone);
			store.get().setLastUpdate(LocalDateTime.now());
			storeRepository.save(store.get());
			StoreDto dto = copyEntityToDto(store.get());
			return dto;
		}
		return null;
	}

	@Override
	public StoreDto updateStorManager(Short storeId, Short managerStaffId) {
		Optional<Store> store = storeRepository.getByStoreId(storeId);
		if (store.isPresent()) {
			store.get().setManagerStaffId(managerStaffId);
			store.get().setLastUpdate(LocalDateTime.now());
			storeRepository.save(store.get());
			StoreDto dto = copyEntityToDto(store.get());
			return dto;
		}
		return null;
	}

	@Override
	public List<StaffDto> getStaffOfStore(Short storeId) {
		List<Staff> list = staffRepository.getByStoreId_storeId(storeId);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : list) {
			StaffDto dto = new StaffDto();
			dto.setStaffId(staff.getStaffId());
			dto.setFirstName(staff.getFirstName());
			dto.setLastName(staff.getLastName());
			dto.setAddressId(staff.getAddressId().getAddressId());
			dto.setPicture(staff.getPicture());
			dto.setEmail(staff.getEmail());
			dto.setStoreId(staff.getStoreId().getStoreId());
			dto.setActive(staff.getActive());
			dto.setUserName(staff.getUsername());
			dto.setPassword(staff.getPassword());
			dto.setLastUpdate(staff.getLastUpdate());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> getCustomersOfStore(Short storeId) {
		List<Customer> list = customerRepository.getByStoreId_storeId(storeId);
		List<CustomerDto> dtos = new ArrayList<>();
		for (Customer cust : list) {
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
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public StaffDto getManagerOfStore(Short id) {
		Optional<Store> byStoreId = storeRepository.getByStoreId(id);
		Optional<Staff> staffId = staffRepository.getByStaffId(byStoreId.get().getManagerStaffId());
		Staff staff = staffId.get();
		StaffDto dto = new StaffDto();
		dto.setStaffId(staff.getStaffId());
		dto.setFirstName(staff.getFirstName());
		dto.setLastName(staff.getLastName());
		dto.setAddressId(staff.getAddressId().getAddressId());
		dto.setPicture(staff.getPicture());
		dto.setEmail(staff.getEmail());
		dto.setStoreId(staff.getStoreId().getStoreId());
		dto.setActive(staff.getActive());
		dto.setUserName(staff.getUsername());
		dto.setPassword(staff.getPassword());
		dto.setLastUpdate(staff.getLastUpdate());
		return dto;
	}

	@Override
	public List<StaffDto> getManagersOfStore() {
		List<Store> findAll = storeRepository.findAll();
		List<StaffDto> managers = new ArrayList<>();
		for (Store store : findAll) {
			Staff staff = staffRepository.getById(store.getManagerStaffId());
			StaffDto dto = new StaffDto();
			dto.setStaffId(staff.getStaffId());
			dto.setFirstName(staff.getFirstName());
			dto.setLastName(staff.getLastName());
			dto.setAddressId(staff.getAddressId().getAddressId());
			dto.setPicture(staff.getPicture());
			dto.setEmail(staff.getEmail());
			dto.setStoreId(staff.getStoreId().getStoreId());
			dto.setActive(staff.getActive());
			dto.setUserName(staff.getUsername());
			dto.setPassword(staff.getPassword());
			dto.setLastUpdate(staff.getLastUpdate());
			managers.add(dto);
		}
		return managers;
	}

	public StoreDto copyEntityToDto(Store store) {
		StoreDto dto = new StoreDto();
		dto.setAddressId(store.getAddressId().getAddressId());
		dto.setManagerStaffId(store.getManagerStaffId());
		dto.setStoreId(store.getStoreId());
		dto.setLastUpdate(store.getLastUpdate());
		return dto;
	}
}
