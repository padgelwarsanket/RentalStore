package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDto {

    private Short addressId;

    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 100, message = "Address must be less than or equal to 100 characters")
    private String address;

    @Size(max = 100, message = "Address2 must be less than or equal to 100 characters")
    private String address2;

    @NotEmpty(message = "District cannot be empty")
    @Size(max = 20, message = "District must be less than or equal to 20 characters")
    private String district;

    @NotNull(message = "City ID cannot be null")
    private Short cityId;

    @NotEmpty(message = "Postal code cannot be empty")
    @Size(max = 10, message = "Postal code must be less than or equal to 10 characters")
    private String postalCode;

    @NotEmpty(message = "Phone cannot be empty")
    @Size(max = 20, message = "Phone must be less than or equal to 20 characters")
    private String phone;

    @NotEmpty(message = "Location cannot be empty")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
            if (addressId == null) {
                throw new IllegalArgumentException("Address ID cannot be null");
            }

            if (address == null || address.isEmpty()) {
                throw new IllegalArgumentException("Address cannot be empty");
            }
            if (address.length() > 100) {
                throw new IllegalArgumentException("Address must be less than or equal to 100 characters");
            }

            if (address2 != null && address2.length() > 100) {
                throw new IllegalArgumentException("Address2 must be less than or equal to 100 characters");
            }

            if (district == null || district.isEmpty()) {
                throw new IllegalArgumentException("District cannot be empty");
            }
            if (district.length() > 20) {
                throw new IllegalArgumentException("District must be less than or equal to 20 characters");
            }

            if (cityId == null) {
                throw new IllegalArgumentException("City ID cannot be null");
            }

            if (postalCode == null || postalCode.isEmpty()) {
                throw new IllegalArgumentException("Postal code cannot be empty");
            }
            if (postalCode.length() > 10) {
                throw new IllegalArgumentException("Postal code must be less than or equal to 10 characters");
            }

            if (phone == null || phone.isEmpty()) {
                throw new IllegalArgumentException("Phone cannot be empty");
            }
            if (phone.length() > 20) {
                throw new IllegalArgumentException("Phone must be less than or equal to 20 characters");
            }

            if (location == null || location.isEmpty()) {
                throw new IllegalArgumentException("Location cannot be empty");
            }
            if (location.length() > 100) {
                throw new IllegalArgumentException("Location must be less than or equal to 100 characters");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}
