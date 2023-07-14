package com.sprint.filmerentalstore.dto;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StaffDto {

//    @NotNull(message = "Staff ID cannot be null")
    private Short staffId;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3,max = 50, message = "First name must be between 3 to 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name must be between 3 to 50 characters")
    private String lastName;

    @NotNull(message = "Address ID cannot be null")
    private Short addressId;

    private String picture;

    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 100, message = "Email must be between 3  to 100 characters")
    private String email;

    @NotNull(message = "Store ID cannot be null")
    private Short storeId;

    @NotEmpty(message = "Active status cannot be empty")
    private String active;

    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 50, message = "Username must be between 3 to 50 characters")
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 50, message = "Password must be between 3 to 50 characters")
    private String password;

    private LocalDateTime lastUpdate;

  
}
