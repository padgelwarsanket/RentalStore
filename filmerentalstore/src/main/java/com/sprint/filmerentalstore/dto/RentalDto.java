package com.sprint.filmerentalstore.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDto {

//    @NotNull(message = "Rental ID cannot be null")
    private Short rentalId;

    //@NotNull(message = "Rental date cannot be null")
    private LocalDateTime rentalDate;

    @NotNull(message = "Inventory ID cannot be null")
    private Short inventoryId;

    @NotNull(message = "Customer ID cannot be null")
    private Short customerId;

    private LocalDate returnDate;

    @NotNull(message = "Staff ID cannot be null")
    private Short staffId;

    private LocalDateTime lastUpdate;

   
}

