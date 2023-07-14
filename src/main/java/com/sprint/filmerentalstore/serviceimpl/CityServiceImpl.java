package com.sprint.filmerentalstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.CityDto;
import com.sprint.filmerentalstore.entity.City;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.CityRepository;
import com.sprint.filmerentalstore.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityRepository repository;
	@Override
	public List<CityDto> getAllCity() {
		List<City> findAll = repository.findAll();
		if(findAll.isEmpty()) {
			throw new FilmRentalStoreException("No record found");
		}
		List<CityDto> dtos = new ArrayList<>();
		for(City city: findAll) {
			CityDto dto = copyEntityToDto(city);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public CityDto copyEntityToDto(City city){
		CityDto dto = new CityDto();
		dto.setCityId(city.getCityId());
		dto.setCity(city.getCity());
		dto.setCountryId(city.getCountryId().getCountryId());
		dto.setLastUpdate(city.getLastUpdate());
		return dto;
	}

}
