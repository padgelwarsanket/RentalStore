package com.sprint.filmerentalstore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sprint.filmerentalstore.dto.PaymentDto;

public interface PaymentService {

	List<PaymentDto> getAllPayment();

	PaymentDto getPaymentById(Short id);

	PaymentDto addPayment(PaymentDto payment);

	List<Map<String, Object>>claculateCumalativeRevenueDatewise();

	public List<Map<String, Object>> getRevenueByDateAndStore(Short storeId);

	List<Map<String, Object>> calculateCumalativeRevenueFilmwise();

	List<Map<String, Object>> getRevenueByFilmId(Short filmId);

	List<Map<String, Object>> getRevenueByStoreId(Short storeId);

	PaymentDto generatePayment(Short inventoryId, Short customerId, Short staffId);

}
