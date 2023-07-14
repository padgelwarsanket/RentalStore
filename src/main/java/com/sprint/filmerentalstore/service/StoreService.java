package com.sprint.filmerentalstore.service;

import java.util.List;

import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.dto.StoreDto;

public interface StoreService {
	
	public List<StoreDto> getAllStore();
	
	public StoreDto registerCustomer(StoreDto dto);

	public StoreDto updateStoreByAddress(Short storeId, Short addressId);

	public List<StoreDto> getStoreByCity(String city);

	public List<StoreDto> getStoreByCountry(String country);

	public StoreDto getStoreByPhone(String phone);

	public StoreDto updateStorePhoneNumber(Short storeId, String phone);

	public StoreDto updateStorManager(Short storeId, Short managerStaffId);

	public List<StaffDto> getStaffOfStore(Short storeId);

	public List<CustomerDto> getCustomersOfStore(Short storeId);

	public StaffDto getManagerOfStore(Short id);

	public List<StaffDto> getManagersOfStore(); 

}
