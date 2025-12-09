package com.jbelt.play.domain.dto;

import com.jbelt.play.domain.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

// Record para el DTO con los atributos que se permiten actualizar
// Usamos anotaciones para indicar a Spring como debe validar con @Valid en el endpoint
// Usamos la anotacion para la validacion y podemos incluir un mensaje
public record UpdateMovieDto(
        @NotBlank(message = "El Título es obligatorio")
        String title,

        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,

        @Min(value = 0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no puede ser mayor que 5")
        Double rating
) {
}
