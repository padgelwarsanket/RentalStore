package com.sprint.filmerentalstore.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CumulativeRevenueNative {
	private LocalDate paymentDate;
	private Double amount;
	private Double sum;

}
