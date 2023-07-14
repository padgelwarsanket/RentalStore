package com.sprint.filmerentalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.filmerentalstore.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Short> {

	Optional<Country> getByCountryId(Short id);
}
