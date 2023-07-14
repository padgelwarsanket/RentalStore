package com.sprint.filmerentalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.filmerentalstore.entity.City;


public interface CityRepository extends JpaRepository<City, Short>{

	
	Optional<City> getByCityId(Short id);

	Optional<City> getByCity(String city);
	

}
