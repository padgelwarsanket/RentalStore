package com.sprint.filmerentalstore.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilmActorDto {

//    @NotNull(message = "Actor ID cannot be null")
    private Short actorId;

    @NotNull(message = "Film ID cannot be null")
    private Short filmId;

    private LocalDateTime lastUpdate;

    public void validate() throws IllegalArgumentException {
        try {
            if (actorId == null) {
                throw new IllegalArgumentException("Actor ID cannot be null");
            }

            if (filmId == null) {
                throw new IllegalArgumentException("Film ID cannot be null");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }
    }
}
