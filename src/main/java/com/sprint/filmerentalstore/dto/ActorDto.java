package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorDto {

	@NotNull(message = "ID cannot be null")
	private Short id;

	@NotEmpty(message = "First name cannot be empty")
	@Size(max = 50, message = "First name must be less than or equal to 50 characters")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	@Size(max = 50, message = "Last name must be less than or equal to 50 characters")
	private String lastName;

	private LocalDateTime lastUpdate;

}
