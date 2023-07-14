package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryDto {

//    @NotNull(message = "Inventory ID cannot be null")
    private Short inventoryId;

    @NotNull(message = "Film ID cannot be null")
    private Short filmId;

    @NotNull(message = "Store ID cannot be null")
    private Short storeId;

    private LocalDateTime lastUpdate;

   
}
