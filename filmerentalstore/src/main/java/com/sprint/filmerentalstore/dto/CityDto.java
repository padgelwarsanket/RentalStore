package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDto {
	
	@NotNull(message = "City ID cannot be null")
	private Short cityId;

	@NotEmpty(message = "City name cannot be empty")
	@Size(max = 50, message = "City name must be less than or equal to 50 characters")
	private String city;

	@NotNull(message = "Country ID cannot be null")
	private Short countryId;

	private LocalDateTime lastUpdate;

}

