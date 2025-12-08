package com.jbelt.play.domain.dto;

// DTO para almacenar el json que viene con la peticion de sugerencias a la IA
public record SuggestRequestDto(
        String userPreferences
) {
}
