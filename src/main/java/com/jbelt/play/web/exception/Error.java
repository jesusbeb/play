package com.jbelt.play.web.exception;

// Record para retornar al cliente REST, el tipo de error y el mensaje
public record Error(
        String typeError,
        String message
) {
}
