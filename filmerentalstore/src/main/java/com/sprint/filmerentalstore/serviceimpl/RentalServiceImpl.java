package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.RentalDto;
import com.sprint.filmerentalstore.entity.Rental;
import com.sprint.filmerentalstore.exception.FilmRentalStoreException;
import com.sprint.filmerentalstore.repository.CustomerRepository;
import com.sprint.filmerentalstore.repository.InventoryRepository;
import com.sprint.filmerentalstore.repository.RentalRepository;
import com.sprint.filmerentalstore.repository.StaffRepository;
import com.sprint.filmerentalstore.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired 
	private InventoryRepository inventoryRepository;
	

	@Override
	public List<RentalDto> getAllRental() {
		List<Rental> rentalList = rentalRepository.findAll();
		if(rentalList.isEmpty()) {
			throw new FilmRentalStoreException("RENTAL NOT FOUND");
		}
		List<RentalDto> dtos = new ArrayList<>();
		for (Rental rental : rentalList) {
			RentalDto dto = copyEntityToDto(rental);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public RentalDto getRentalById(Short id) {
		Rental rental = rentalRepository.findRentalByRentalId(id);
		RentalDto dto = copyEntityToDto(rental);
		return dto;
	}

	public RentalDto addRental(RentalDto dto) {
		Rental rental = new Rental();
		rental.setCustomerId(customerRepository.getById(dto.getCustomerId()));
		rental.setInventoryId(inventoryRepository.getById(dto.getInventoryId()));
		rental.setStaffId(staffRepository.getById(dto.getStaffId()));
		rental.setLastUpdate(LocalDateTime.now());
		rental.setRentalDate(LocalDateTime.now());
		Rental save = rentalRepository.save(rental);
		return copyEntityToDto(save);
	}

	public List<String> getFilmsByCustomer(Short customerId) {
		return rentalRepository.findFilmsByCustomer(customerId);
	}

	public List<String> getTopTenRentedFilms() {
		return rentalRepository.findTopTenRentedFilms();
	}

	public List<String> getTopTenRentedFilmsByStore(Short storeId) {
		return rentalRepository.findTopTenRentedFilmsByStore(storeId);
	}

	public List<String> getCustomersWithPendingReturns(Short storeId) {
		return rentalRepository.findCustomersWithPendingReturns(storeId);
	}

	public void updateReturnDate(Short rentalId, LocalDate returnDate) {
		Rental rental = rentalRepository.getById(rentalId);
		rental.setReturnDate(returnDate);
		rentalRepository.save(rental);
	}
	
	public static RentalDto copyEntityToDto(Rental rental){
		RentalDto dto = new RentalDto();
		dto.setCustomerId(rental.getCustomerId().getCustomerId());
		dto.setInventoryId(rental.getInventoryId().getInventoryId());
		dto.setRentalId(rental.getRentalId());
		dto.setRentalDate(rental.getRentalDate());
		dto.setLastUpdate(rental.getLastUpdate());
		dto.setStaffId(rental.getStaffId().getStaffId());
		return dto;
	}
}
