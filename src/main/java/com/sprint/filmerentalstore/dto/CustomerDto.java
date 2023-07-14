package com.sprint.filmerentalstore.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    private Short customerId;

    @NotNull(message = "Stores ID cannot be null")
    private Short storeId;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3 ,max = 50, message = "First name must be between 3 to 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 3 ,max = 50, message = "Last name must be  between 3 to 50 characters")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Size(min = 10 ,max = 100, message = "Email must be  between 3 to 50 characters")
    private String email;

    @NotNull(message = "Address ID cannot be null")
    private Short addressId;

    @NotEmpty(message = "Active status cannot be empty")
    private String active;

    //@NotNull(message = "Create date cannot be null")
    private LocalDate createDate;

    private LocalDateTime lastUpdate;


}