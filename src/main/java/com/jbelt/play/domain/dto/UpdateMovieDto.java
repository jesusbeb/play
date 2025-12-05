package com.jbelt.play.domain.dto;

import com.jbelt.play.domain.Genre;

import java.time.LocalDate;

// Record para el DTO con los atributos que se permiten actualizar
public record UpdateMovieDto(
        String title,
        LocalDate releaseDate,
        Double rating
) {
}
