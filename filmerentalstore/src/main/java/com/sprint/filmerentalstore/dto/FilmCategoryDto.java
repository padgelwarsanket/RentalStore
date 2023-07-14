
package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilmCategoryDto {

//    @NotNull(message = "Film ID cannot be null")
    private Short filmId;

    @NotNull(message = "Category ID cannot be null")
    private Byte categoryId;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
            if (filmId == null) {
                throw new IllegalArgumentException("Film ID cannot be null");
            }

            if (categoryId == null) {
                throw new IllegalArgumentException("Category ID cannot be null");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}