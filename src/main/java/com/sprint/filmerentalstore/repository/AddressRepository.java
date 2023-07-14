package com.sprint.filmerentalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Short>{

	Optional<Address> getByAddressId(Short id);
	Optional<Address> getByCityId_cityId(Short cityId);
}
