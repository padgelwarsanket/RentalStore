package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LanguageDto {

//    @NotNull(message = "Language ID cannot be null")
    private Byte languageId;

    @NotEmpty(message = "Language name cannot be empty")
    @Size(max = 50, message = "Language name must be less than or equal to 50 characters")
    private String name;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
            
            if (languageId == null) {
                throw new IllegalArgumentException("Language ID cannot be null");
            }

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Language name cannot be empty");
            }
            if (name.length() > 50) {
                throw new IllegalArgumentException("Language name must be less than or equal to 50 characters");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}
