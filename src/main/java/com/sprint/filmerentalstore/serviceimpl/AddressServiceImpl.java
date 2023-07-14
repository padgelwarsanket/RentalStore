package com.sprint.filmerentalstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.AddressRepository;

@Service
public class AddressServiceImpl {

	@Autowired
	private AddressRepository repository;
	
	public List<AddressDto> getAllAddress() {
		 List<Address> all = repository.findAll();
		 if(all.isEmpty()) {
			 throw new FilmRentalStoreException("No record Found");
		 }
		 List<AddressDto> dtos = new ArrayList<>();
		 for(Address add: all) {
			 AddressDto dto = copyEntityToDto(add);
			 dtos.add(dto);
		 }
		 return dtos;
	}
	
	public AddressDto getAddressById(Short id){
		Optional<Address> byId = repository.findById(id);
		if(byId.isPresent()) {
			Address add = byId.get();
			AddressDto dto = copyEntityToDto(add);
			//BeanUtils.copyProperties(add, dto);
			return dto;
		}
		throw new FilmRentalStoreException("Address does not exist");
	}
	
	public AddressDto copyEntityToDto(Address address){
		AddressDto dto = new AddressDto();
		dto.setAddress(address.getAddress());
		dto.setAddress2(address.getAddress2());
		dto.setAddressId(address.getAddressId());
		dto.setCityId(address.getCityId().getCityId());
		dto.setDistrict(address.getDistrict());
		dto.setLastUpdate(address.getLastUpdate());
		dto.setLocation(address.getLocation());
		dto.setPhone(address.getPhone());
		dto.setPostalCode(address.getPostal_code());
		return dto;
		
	}

}
