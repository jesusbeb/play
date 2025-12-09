package com.jbelt.play.domain.exception;

// Clase para crear excepciones personalizadas
public class MovieAlreadyExistsException extends RuntimeException{

    // Constructor, recibimos el titulo de la pelicula que
    // servira para hacer una descripcion del error
    public MovieAlreadyExistsException(String movieTitle){
        super("La pelicula \"" +movieTitle + "\" ya existe!");
    }
}
