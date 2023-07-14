package com.sprint.filmerentalstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.CountryDto;
import com.sprint.filmerentalstore.entity.Country;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.CountryRepository;
import com.sprint.filmerentalstore.service.CountryService;


@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository repository;

	@Override
	public List<CountryDto> getAllCountry() {
		List<Country> list = repository.findAll();
		if(list.isEmpty()) {
			throw new FilmRentalStoreException("No record found");
		}
		List<CountryDto> dtos = new ArrayList<>();
		for(Country emp: list) {
			CountryDto dto = copyEntityToDto(emp);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public CountryDto copyEntityToDto(Country country){
		CountryDto dto = new CountryDto();
		dto.setCountry(country.getCountry());
		dto.setCountryId(country.getCountryId());
		dto.setLast_update(country.getLastUpdate());
		return dto;
	}

}
