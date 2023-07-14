package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.CustomerDto;
import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.entity.Store;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.AddressRepository;
import com.sprint.filmerentalstore.repository.StaffRepository;
import com.sprint.filmerentalstore.repository.StoreRepository;
import com.sprint.filmerentalstore.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StoreRepository storeRepository;
	
	
	@Override

    public StaffDto updateStaff(Short staffId, StaffDto dto) {

        Optional<Staff> staff = repository.findById(staffId);

        System.out.println(staff);

//        staff.setAddressId(addressRepository.getById(dto.getAddressId()));

//        staff.setEmail(dto.getEmail());

//        staff.setFirstName(dto.getFirstName());

//        staff.setLastName(dto.getLastName());
        staff.get().setLastUpdate(LocalDateTime.now());
        BeanUtils.copyProperties(dto, staff.get());

        //staff.setLastUpdate(dto.getLastUpdate());

        repository.save(staff.get());

        dto.setStaffId(staffId);

        return dto;
	}

	// @Override
	public List<StaffDto> getAllStaff() {
		List<Staff> staffList = repository.findAll();
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : staffList) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public StaffDto addStaff(StaffDto staffDto) {
		Staff staff = new Staff();
		staff.setStoreId(storeRepository.getById(staffDto.getStoreId()));
		staff.setAddressId(addressRepository.getById(staffDto.getAddressId()));
		staff.setFirstName(staffDto.getFirstName());
		staff.setLastName(staffDto.getLastName());
		staff.setPicture(staffDto.getPicture());
		staff.setEmail(staffDto.getEmail());
		staff.setActive(staffDto.getActive());
		staff.setUsername(staffDto.getUsername());
		staff.setPassword(staffDto.getPassword());
		staff.setLastUpdate(LocalDateTime.now());
		repository.save(staff);
		return staffDto;

	}

	public List<StaffDto> getStaffByLastName(String lastName) {
		List<Staff> byLastName = repository.getStaffByLastName(lastName);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : byLastName) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}

		return dtos;
	}

//
	public List<StaffDto> getStaffByFirstName(String firstName) {
		List<Staff> byFirstName = repository.getStaffByFirstName(firstName);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : byFirstName) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<StaffDto> getStaffByEmail(String email) {
		Optional<Staff> staff = repository.getStaffByEmail(email);
		List<StaffDto> dtos = new ArrayList<>();
		if (!staff.isPresent()) {
			throw new FilmRentalStoreException("EMAIL NOT FOUND");
		}
		StaffDto dto = copyEntityToDto(staff.get());
		dtos.add(dto);
		return dtos;
	}

	@Override
	public StaffDto updateStaffAddress(Short staffId, Short addressId) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		if (staff.isPresent()) {
			Optional<Address> byAddressId = addressRepository.getByAddressId(addressId);
			staff.get().setAddressId(byAddressId.get());
			staff.get().setLastUpdate(LocalDateTime.now());
			repository.save(staff.get());
			StaffDto dto = copyEntityToDto(staff.get());
			return dto;
		}
		return null;
	}

	@Override
	public List<StaffDto> getStaffByCity(String city) {
		List<Staff> findByAddressId_cityId = repository.getByAddressId_cityId_city(city);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : findByAddressId_cityId) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<StaffDto> getStaffByCountry(String country) {
		List<Staff> findByAddressId_cityId = repository.getByAddressId_cityId_countryId_country(country);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : findByAddressId_cityId) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<StaffDto> getStaffByPhone(String phone) {
		List<Staff> findByPhone = repository.getByAddressId_Phone(phone);
		List<StaffDto> dtos = new ArrayList<>();
		for (Staff staff : findByPhone) {
			StaffDto dto = copyEntityToDto(staff);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public StaffDto updateStaffFirstName(Short staffId, String firstName) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		if (staff.isPresent()) {
			staff.get().setFirstName(firstName);
			staff.get().setLastUpdate(LocalDateTime.now());
			repository.save(staff.get());
			StaffDto dto = copyEntityToDto(staff.get());
			return dto;
		}
		return null;
	}

	@Override
	public StaffDto updateStaffLastName(Short staffId, String lastName) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		if (staff.isPresent()) {
			staff.get().setLastName(lastName);
			staff.get().setLastUpdate(LocalDateTime.now());
			repository.save(staff.get());
			StaffDto dto = copyEntityToDto(staff.get());
			return dto;
		}
		return null;
	}

	@Override
	public StaffDto updateStaffEmail(Short staffId, String email) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		if (staff.isPresent()) {
			staff.get().setEmail(email);
			staff.get().setLastUpdate(LocalDateTime.now());
			repository.save(staff.get());
			StaffDto dto = copyEntityToDto(staff.get());
			return dto;
		}
		return null;
	}

	@Override
	public StaffDto updateStoreToStaff(Short staffId, Short storeId) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		if (staff.isPresent()) {
			Optional<Store> byStoreId = storeRepository.getByStoreId(storeId);
			staff.get().setStoreId(byStoreId.get());
			staff.get().setLastUpdate(LocalDateTime.now());
			repository.save(staff.get());
			StaffDto dto = copyEntityToDto(staff.get());
			return dto;
		}
		return null;
	}

	@Override
	public StaffDto updateStaffPhoneNumber(Short staffId, String phone) {
		Optional<Staff> staff = repository.getByStaffId(staffId);
		staff.get().getAddressId().setPhone(phone);
		staff.get().setLastUpdate(LocalDateTime.now());
		StaffDto dto = copyEntityToDto(staff.get());
		return dto;
	}

	public StaffDto copyEntityToDto(Staff staff) {
		StaffDto dto = new StaffDto();
		dto.setStaffId(staff.getStaffId());
		dto.setFirstName(staff.getFirstName());
		dto.setLastName(staff.getLastName());
		dto.setAddressId(staff.getAddressId().getAddressId());
		dto.setPicture(staff.getPicture());
		dto.setEmail(staff.getEmail());
		dto.setStoreId(staff.getStoreId().getStoreId());
		dto.setActive(staff.getActive());
		dto.setUsername(staff.getUsername());
		dto.setPassword(staff.getPassword());
		dto.setLastUpdate(staff.getLastUpdate());
		return dto;
	}

}
