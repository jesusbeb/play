package com.jbelt.play.web.exception;

import com.jbelt.play.domain.exception.MovieAlreadyExistsException;
import com.jbelt.play.domain.exception.MovieNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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

    // Manejador de excepciones, para la excepcion MethodArgumentNotValidException que se produce cuando
    // un parametro no cumple con la validacion de la anotacion indicada (en el DTO). Retorna un List<Error>
    // getBindingResult es el resultado de las validaciones erroneas, getFieldErrors nos da la lista de los
    // errores de cada campo, hacemos forEach, y para cada error lo agregamos en la lista, obteniendo su campo con
    // gerField y su mensaje por default que es el que se puso en las anotaciones del DTO.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> hanleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    // Manejador de excepciones para las excepciones generales, las que no estan controladas de
    // manera especifica con algun metodo
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex){
        Error error = new Error("unknown-error", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}
