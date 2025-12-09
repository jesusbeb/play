package com.jbelt.play.web.exception;

import com.jbelt.play.domain.exception.MovieAlreadyExistsException;
import com.jbelt.play.domain.exception.MovieNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Clase para manejar las excepciones Rest, la anotamos para indicarla a Spring
@RestControllerAdvice
public class RestExceptionHandler {

    // Metodo que retornara un ResponseEntity de tipo Error (record)
    // Con @ExceptionHandler indicamos la clase de excepcion que va a controlar Spring
    // Creamos una instancia de Error y enviamos un nombre para el tipo de error, junto
    // con el mensaje que arroja la excepcion
    // Retornamos el error en un ResponseEntity, usamos badRequest para enviar el cuerpo que
    // es el record error
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleException(MovieAlreadyExistsException ex){
        Error error = new Error("movie-already-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    // Excepcion para una pelicula no encontrada por su id
    @ExceptionHandler(MovieNotFound.class)
    public ResponseEntity<Error> handleException(MovieNotFound ex){
        return ResponseEntity.badRequest().body( new Error("movie-not-found", ex.getMessage()) );
    }

}
