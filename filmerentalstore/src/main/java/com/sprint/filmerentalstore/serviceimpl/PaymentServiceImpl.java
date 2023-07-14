package com.sprint.filmerentalstore.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.filmerentalstore.dto.PaymentDto;
import com.sprint.filmerentalstore.entity.Customer;
import com.sprint.filmerentalstore.entity.Inventory;
import com.sprint.filmerentalstore.entity.Payment;
import com.sprint.filmerentalstore.entity.Rental;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.repository.CustomerRepository;
import com.sprint.filmerentalstore.repository.InventoryRepository;
import com.sprint.filmerentalstore.repository.PaymentRepository;
import com.sprint.filmerentalstore.repository.RentalRepository;
import com.sprint.filmerentalstore.repository.StaffRepository;
import com.sprint.filmerentalstore.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	public List<PaymentDto> getAllPayment() {
		List<Payment> paymentList = paymentRepository.findAll();
		List<PaymentDto> dtos = new ArrayList<>();
		for (Payment payment : paymentList) {
			PaymentDto dto = copyEntityToDto(payment);
			dtos.add(dto);
		}

		return dtos;

	}

	@Override
	public PaymentDto getPaymentById(Short id) {
		Optional<Payment> payment = paymentRepository.findByPaymentId(id);
		PaymentDto dto = copyEntityToDto(payment.get());
		return dto;
	}

	@Override
	public PaymentDto addPayment(PaymentDto dto) {
		Payment paym = new Payment();
		paym.setStaffId(staffRepository.getById(dto.getStaffId()));
		paym.setRentalId(rentalRepository.getById(dto.getRentalId()));
		paym.setCustomerId(customerRepository.getById(dto.getCustomerId()));
		paym.setAmount(dto.getAmount());
		paym.setPaymentDate(LocalDateTime.now());
		paym.setLastUpdate(LocalDateTime.now());
		Payment save = paymentRepository.save(paym);
		return copyEntityToDto(save);
	}

	@Override
	public List<Map<String, Object>> claculateCumalativeRevenueDatewise() {
		return paymentRepository.calculateCumulativeRevenueDatewise();

	}

	@Override
	public List<Map<String, Object>> getRevenueByDateAndStore(Short storeId) {
		List<Map<String,Object>> byStore = paymentRepository.calculateCumulativeRevenueDatewiseByStore(storeId);
		return byStore;
	}

	@Override
	public List<Map<String, Object>> calculateCumalativeRevenueFilmwise() {
		return paymentRepository.calculateCumulativeRevenueFilmwise();
	}

	@Override
	public List<Map<String, Object>> getRevenueByFilmId(Short filmId) {
		return paymentRepository.calculateCumulativeRevenueByFilmId(filmId);
		// return null;
	}

	@Override
	public List<Map<String, Object>> getRevenueByStoreId(Short storeId) {
		// Store store = storeRepository.getById(storeId);
		return paymentRepository.calculateCumulativeRevenueByStoreId(storeId);
		//return null;
	}
	
	public PaymentDto generatePayment(Short filmId,Short custId, Short staffId) {
		 PaymentDto dto = new PaymentDto();
		 Inventory invById = inventoryRepository.findFirstByFilmId_FilmId(filmId);
		 Optional<Customer> custById = customerRepository.findById(custId);
		 Optional<Staff> staffById = staffRepository.findById(staffId);
		 Rental rental = new Rental();
		 rental.setCustomerId(custById.get());
		 rental.setInventoryId(invById);
		 rental.setStaffId(staffById.get());
		 rental.setLastUpdate(LocalDateTime.now() );
		 rental.setRentalDate(LocalDateTime.now());
		 Rental save = rentalRepository.save(rental);
		 System.out.println(save.toString());
		 Payment payment = new Payment();
		 payment.setCustomerId(custById.get());
		 payment.setRentalId(rental);
		 payment.setStaffId(staffById.get());
		 payment.setAmount((double) 1500);
		 payment.setLastUpdate(LocalDateTime.now());
		 payment.setPaymentDate(LocalDateTime.now());
		 Payment save2 = paymentRepository.save(payment);
		 System.out.println(save2.toString());
		 return copyEntityToDto(save2);
		 }

	public PaymentDto copyEntityToDto(Payment entity) {
		PaymentDto dto = new PaymentDto();
		dto.setCustomerId(entity.getCustomerId().getCustomerId());
		dto.setStaffId(entity.getStaffId().getStaffId());
		dto.setRentalId(entity.getRentalId().getRentalId());
		dto.setPaymentId(entity.getPaymentId());
		dto.setAmount(entity.getAmount());
		dto.setLastUpdate(entity.getLastUpdate());
		dto.setPaymentDate(entity.getPaymentDate());
		return dto;
	}
}
