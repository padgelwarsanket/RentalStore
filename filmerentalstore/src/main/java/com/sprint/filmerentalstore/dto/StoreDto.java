package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreDto {

//    @NotNull(message = "Store ID is required")
    private Short storeId;

    @NotNull(message = "Manager staff ID is required")
    private Short managerStaffId;

    @NotNull(message = "Address ID is required")
    private Short addressId;

    private LocalDateTime lastUpdate;

    
}