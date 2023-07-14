package com.sprint.filmerentalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Short> {

	Optional<Staff> getStaffByEmail(String email);

	List<Staff> getStaffByLastName(String lastName);

	List<Staff> getStaffByFirstName(String firstName);

	Optional<Staff> getByStaffId(Short id);

	List<Staff> getByAddressId_cityId_city(String city);

	List<Staff> getByAddressId_cityId_countryId_country(String country);

	List<Staff> getByAddressId_Phone(String phone);

	List<Staff> getByStoreId_storeId(Short storeId);

}
