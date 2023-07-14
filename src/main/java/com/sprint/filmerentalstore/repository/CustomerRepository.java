package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short> {

	Optional<Customer> getByCustomerId(Short id);

	List<Customer> getByLastName(String lastName);

	List<Customer> getByFirstName(String name);

	Optional<Customer> getByEmail(String email);

	List<Customer> getByAddressId_cityId_city(String city);

	List<Customer> getByAddressId_cityId_countryId_country(String country);

	List<Customer> getByActive(String str);

	Optional<Customer> getByAddressId_Phone(String phone);

	List<Customer> getByStoreId_storeId(Short storeId);
}
