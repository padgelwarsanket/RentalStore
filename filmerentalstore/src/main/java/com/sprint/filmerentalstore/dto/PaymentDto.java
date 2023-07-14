package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentDto {

//    @NotNull(message = "Payment ID cannot be null")
    private Short paymentId;

    @NotNull(message = "Staff ID cannot be null")
    private Short staffId;

    @NotNull(message = "Rental ID cannot be null")
    private Short rentalId;

    @NotNull(message = "Customer ID cannot be null")
    private Short customerId;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive value")
    private Double amount;

   // @NotNull(message = "Payment date cannot be null")
    private LocalDateTime paymentDate;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
           
            if (paymentId == null) {
                throw new IllegalArgumentException("Payment ID cannot be null");
            }

            if (staffId == null) {
                throw new IllegalArgumentException("Staff ID cannot be null");
            }

            if (rentalId == null) {
                throw new IllegalArgumentException("Rental ID cannot be null");
            }

            if (customerId == null) {
                throw new IllegalArgumentException("Customer ID cannot be null");
            }

            if (amount == null) {
                throw new IllegalArgumentException("Amount cannot be null");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be a positive value");
            }

            if (paymentDate == null) {
                throw new IllegalArgumentException("Payment date cannot be null");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}
