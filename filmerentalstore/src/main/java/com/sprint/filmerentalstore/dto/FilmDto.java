package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import com.sprint.filmerentalstore.entity.Language;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FilmDto {

//    @NotNull(message = "Film ID cannot be null")
    private Short filmId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 255, message = "Title must be less than or equal to 255 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

//    @NotEmpty(message = "Release year cannot be empty")
//    @Size(max = 4, message = "Release year must be 4 characters")
    private String releaseYear;

    @NotNull(message = "Language ID cannot be null")
    private Byte languageId;

    private Language originalLanguageId;

    @NotNull(message = "Rental duration cannot be null")
    @Positive(message = "Rental duration must be a positive value")
    private Integer rentalDuration;

    @NotNull(message = "Rental rate cannot be null")
    @Positive(message = "Rental rate must be a positive value")
    private Double rentalRate;

    @Positive(message = "Length must be a positive value")
    private Integer length;

    @NotNull(message = "Replacement cost cannot be null")
    @Positive(message = "Replacement cost must be a positive value")
    private Double replacementCost;

    @Size(max = 10, message = "Rating must be less than or equal to 10 characters")
    private String rating;

    @Size(max = 100, message = "Special features must be less than or equal to 100 characters")
    private String specialFeatures;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
            if (filmId == null) {
                throw new IllegalArgumentException("Film ID cannot be null");
            }

            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            if (title.length() > 255) {
                throw new IllegalArgumentException("Title must be less than or equal to 255 characters");
            }

            if (description == null || description.isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }

            if (releaseYear == null || releaseYear.isEmpty()) {
                throw new IllegalArgumentException("Release year cannot be empty");
            }
            if (releaseYear.length() != 4) {
                throw new IllegalArgumentException("Release year must be 4 characters");
            }

            if (languageId == null) {
                throw new IllegalArgumentException("Language ID cannot be null");
            }

            if (rentalDuration == null) {
                throw new IllegalArgumentException("Rental duration cannot be null");
            }
            if (rentalDuration <= 0) {
                throw new IllegalArgumentException("Rental duration must be a positive value");
            }

            if (rentalRate == null) {
                throw new IllegalArgumentException("Rental rate cannot be null");
            }
            if (rentalRate <= 0) {
                throw new IllegalArgumentException("Rental rate must be a positive value");
            }

            if (length != null && length <= 0) {
                throw new IllegalArgumentException("Length must be a positive value");
            }

            if (replacementCost == null) {
                throw new IllegalArgumentException("Replacement cost cannot be null");
            }
            if (replacementCost <= 0) {
                throw new IllegalArgumentException("Replacement cost must be a positive value");
            }

            if (rating != null && rating.length() > 10) {
                throw new IllegalArgumentException("Rating must be less than or equal to 10 characters");
            }

            if (specialFeatures != null && specialFeatures.length() > 100) {
                throw new IllegalArgumentException("Special features must be less than or equal to 100 characters");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}