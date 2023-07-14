package com.sprint.filmerentalstore.dto;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CountryDto {
	
	@NotNull(message = "Country ID cannot be null")
	private Short countryId;

	@NotBlank(message = "Country name cannot be empty")
	@Size(max = 50, message = "Country name must be less than or equal to 50 characters")
	private String country; 

	private LocalDateTime last_update;

}