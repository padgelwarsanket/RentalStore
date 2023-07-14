
package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
	
	@NotNull(message = "Category ID cannot be null")
	private Byte categoryId;

	@NotEmpty(message = "Name cannot be empty")
	@Size(max = 50, message = "Name must be less than or equal to 50 characters")
	private String name;

	private LocalDateTime lastUpdate;

}



