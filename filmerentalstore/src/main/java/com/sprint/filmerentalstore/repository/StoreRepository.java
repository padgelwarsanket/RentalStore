package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store,Short>{

	Optional<Store> getByStoreId(Short id);
	List<Store> getByAddressId(Address addressId);
	//List<Store> getByAddressId_cityId_city(String city);
	List<Store> getByAddressId_cityId_city(String city);
	List<Store> getByAddressId_cityId_countryId_country(String country);
	Optional<Store> getByAddressId_phone(String phone);

}
